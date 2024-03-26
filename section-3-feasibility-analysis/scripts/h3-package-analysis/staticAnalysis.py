import os
import subprocess
import json
import shutil
import xml.etree.ElementTree as ET

codeql = "" #Path to codeql executable
base_dir = "" #Path to base directory to hold working components
custom_query_dir = "" #Path to root of custom codeql queries
output_file = "" #Path to desired output file
delete_repos = False #Set to True if you don't want access to the repos after running
repo_base_dir = os.path.join(base_dir, "repos4analysis")
results_base_dir = os.path.join(base_dir, "sarif_results")

def clone_repo(url, repo_dir):
    try:
        subprocess.run(["git", "clone", url, repo_dir], check=True, timeout=600)
    except subprocess.TimeoutExpired:
            print("Clone Failed due to Timeout")

def build_codeql_database(repo_dir, codeql_db_name):
    os.chdir(repo_dir)
    try:
        subprocess.run([codeql, "database", "create", codeql_db_name, "--language=java", "--overwrite"], check=True)
    except subprocess.CalledProcessError as e:
        print(f"Error building CodeQL database: {e}")
        if delete_repos is True:
            delete_repo(repo_dir)
        return False
    return True

def run_codeql_query(codeql_db_name, query_paths, codeql_output):
    try:
        subprocess.run([codeql, "database", "analyze", codeql_db_name, *query_paths, "--format=sarif-latest", f"--output={codeql_output}", "--rerun"], check=True)
    except subprocess.CalledProcessError as e:
        print(f"Error running CodeQL query: {e}")
        return False
    return True

def parse_sarif_file(sarif_path):
    # Replace with actual SARIF parsing logic

    try:
        with open(sarif_path, "r") as file:

            json_data = file.read()

        sarif_data = json.loads(json_data)

        findings_count = {"file-read": 0, "file-write": 0, "socket-connect": 0, "socket-server": 0, "runtime-exec": 0, "jndi-lookup" : 0,"jndi-bind" : 0, "object-parse" : 0, "xml-parse": 0, "threading" : 0, "system-load": 0}

        runs = sarif_data.get('runs', [])
        for run in runs:
            results = run.get('results', [])
            for result in results:
                rule_id = result.get('ruleId', 'N/A')

                # Check if the result is from a test directory
                locations = result.get('locations', [])
                in_test_directory = any(
                    "src/test/java" in loc.get("physicalLocation", {}).get("artifactLocation", {}).get("uri", "")
                    for loc in locations
                )

                if not in_test_directory:
                    if rule_id == "file-read-analysis":
                        findings_count["file-read"] += 1
                    elif rule_id == "file-write-analysis":
                        findings_count["file-write"] += 1
                    elif rule_id == "socket-connect-analysis":
                        findings_count["socket-connect"] += 1
                    elif rule_id == "socket-server-analysis":
                        findings_count["socket-server"] += 1
                    elif rule_id == "runtime-exec-analysis":
                        findings_count["runtime-exec"] += 1
                    elif rule_id == "jndi-lookup-analysis":
                        findings_count["jndi-lookup"] += 1
                    elif rule_id == "objectinputstream-readObject-analysis":
                        findings_count["object-parse"] += 1
                    elif rule_id == "documentbuilder-parse-analysis":
                        findings_count["xml-parse"] += 1
                    elif rule_id == "threading-analysis":
                        findings_count["threading"] += 1
                    elif rule_id == "system-load-analysis":
                        findings_count["system-load"] += 1
                    elif rule_id == "jndi-bind-analysis":
                        findings_count["jndi-bind"] += 1

        return findings_count

    except FileNotFoundError or json.JSONDecodeError as e:
        print(f"Error parsing Sarif file {e}")
        return None

def delete_repo(repo_dir):
    os.chdir(base_dir)
    subprocess.run(["sudo", "rm", "-rf", repo_dir], check = True)

def get_package_name_from_pom(xml_file_path):
    try:
        # Parse the XML file
        tree = ET.parse(xml_file_path)
        root = tree.getroot()

        # get the namesapce from the root key
        namesp = root.tag.replace("project","")  

        # Get artifactId from root  
        artifactId_element = root.find(f"{namesp}artifactId")
        if artifactId_element is None:
            return None

        artifactId = artifactId_element.text

        # Get groupId from root or parent tag
        groupId_element = root.find(f"{namesp}groupId")
        if groupId_element is None:
            parent = root.find(f"{namesp}parent")
            if parent is None:
                print("Parent was None while parsing XML")
                return None
            groupId_element = parent.find(f"{namesp}groupId")
        if groupId_element is None:
            groupId_element = root.find(f".//{namesp}groupId")
        if groupId_element is None:
            return None

        groupId = groupId_element.text
        
        return f"{groupId}:{artifactId}"
    except ET.ParseError as e:
        print(f"Error parsing XML: {e}")
        return None


def get_package_dir(repo_path, package_name):
    """Returns the path to the subdirectory containing a pom.xml file that matches the provided package name within a repository.

    Args:
        repo_path: The path to the repository root directory.
        package_name: The maven package name (groupId:artifactId).

    Returns:
        The path to the subdirectory containing the matching pom.xml file, or None if not found.
    """

    for root, _, files in os.walk(repo_path):
        for file in files:
            if file.lower() == "pom.xml":
                pom_path = os.path.join(root, file)
                current_package_name = get_package_name_from_pom(pom_path)
                if current_package_name == package_name:
                    return os.path.dirname(pom_path)

    return None


def process_url(url_components, query_path, output_file):
    url = url_components[0]
    package_name = url_components[1]
    repo_name = url.split("/")[-1].replace(".git", "")
    repo_dir = os.path.join(repo_base_dir, repo_name)
    codeql_db_name = os.path.join(repo_dir, "codeql_db")
    codeql_output = os.path.join(results_base_dir, f"{repo_name}-results.sarif")

    print(f"Processing {repo_name}")

    if not os.path.exists(repo_dir):
        try:
            clone_repo(url, repo_dir)
        except subprocess.CalledProcessError:
            with open(output_file, "a") as f:
                f.write(f"{repo_name}, Cloning failed\n")
            return

    package_dir = get_package_dir(repo_dir, package_name)

    if package_dir is None:
        with open(output_file, "a") as f:
            f.write(f"{package_name}, Pom.xml not found. Continuing...\n")
            package_dir = repo_dir

    if not build_codeql_database(package_dir, codeql_db_name):
        with open(output_file, "a") as f:
            f.write(f"{package_name}, CodeQL build failed\n")
        return

    if not run_codeql_query(codeql_db_name, query_path, codeql_output):
        with open(output_file, "a") as f:
            f.write(f"{package_name}, CodeQL query failed\n")
        return

    sarif_output = parse_sarif_file(codeql_output)

    with open(output_file, "a") as f:
        f.write(f"{package_name}, {sarif_output}\n")

    if delete_repos:
        delete_repo(repo_dir)

if __name__ == "__main__":
    url_file = f"./dbResults/dbResults_{number}.txt"
    query_paths = [f"{custom_query_dir}/file-write.ql",
                    f"{custom_query_dir}/file-read.ql",
                    f"{custom_query_dir}/untime-exec.ql",
                    f"{custom_query_dir}/socket-connect.ql",
                    f"{custom_query_dir}/socket-server.ql",
                    f"{custom_query_dir}/jndi-lookup.ql",
                    f"{custom_query_dir}/object-readObject.ql",
                    f"{custom_query_dir}/xml-parse.ql",
                    f"{custom_query_dir}/threading.ql",
                    f"{custom_query_dir}/system-load.ql",
                    f"{custom_query_dir}/jndi-bind.ql"]
    

    with open(url_file, "r") as f:
        urls = f.readlines()

    with open(output_file, "a") as f:
        f.write("Analyzing package capabilities\n\n")

    for url_object in urls:
        url_components = url_object.split()
        process_url(url_components, query_paths, output_file)
