import subprocess
import time
import random

timestamp = int(time.time())
output_filename = "profile_" + str(timestamp) + ".txt"
log_filename = "profile_" + str(timestamp) + ".log"
policy_generation = False

def execute_shell_command(command):
    process = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    output, error = process.communicate()
    return output.decode().strip()

def run_shell_command(command):
    profile_dict = {}

    start_time = int(execute_shell_command("date '+%s%3N'"))
    profile_dict[ProfileKey.PROGRAM_START.name] = start_time

    output = execute_shell_command(command)
    write_to_file(output + "\n", log_filename)

    stop_time = int(execute_shell_command("date '+%s%3N'"))
    profile_dict[ProfileKey.PROGRAM_END.name] = stop_time

    for line in output.split('\n'):
        if line.startswith("[PROFILING]"):
            entries = line.split()
            if len(entries) >= 3:
                profile_dict[entries[1]] = int(entries[2])

    return profile_dict

# Create a function to calculate the average profile from a list of profiles
def calculate_average_profile(profiles):
    average_profile = {}
    num_profiles = len(profiles)

    if num_profiles == 0:
        return average_profile

    # Sum the values of each profile key
    for key in profiles[0]:
        sum_value = sum(profile[key] for profile in profiles)
        average_value = sum_value / num_profiles
        average_profile[key] = average_value

    return average_profile

def process_profile(profile):
    # print(f"Using {agent}")

    start_main = profile[ProfileKey.MAIN_CALLED.name] - profile[ProfileKey.PROGRAM_START.name]
    # print("Start time to Main: " + str(start_main))

    if (ProfileKey.AGENT_CALLED.name in profile):

        start_agent = profile[ProfileKey.AGENT_CALLED.name] - profile[ProfileKey.PROGRAM_START.name]
        # print("Start time to Agent called: " + str(start_agent))

        agent_setup = profile[ProfileKey.AGENT_EXITING.name] - profile[ProfileKey.AGENT_CALLED.name]
        # print("Agent setup time: " + str(agent_setup))

    else:
        start_agent = 0
        agent_setup = 0

    if (ProfileKey.FILE_TRANSFORMER_EXITING.name in profile):
        file_trans = profile[ProfileKey.FILE_TRANSFORMER_EXITING.name] - profile[ProfileKey.FILE_TRANSFORMER_CALLED.name]
        # print("File transformation time: " + str(file_trans))
    else:
        file_trans = 0
    
    if (ProfileKey.APP_CALLED.name in profile and ProfileKey.APP_EXITING.name in profile):
        main_exec = profile[ProfileKey.APP_EXITING.name] - profile[ProfileKey.APP_CALLED.name]
    else:
        main_exec = profile[ProfileKey.MAIN_EXITING.name] - profile[ProfileKey.MAIN_CALLED.name]
    # print("Main execution time: " + str(main_exec))
    
    total_exec = profile[ProfileKey.PROGRAM_END.name] - profile[ProfileKey.PROGRAM_START.name]
    # print("Total execution time: " + str(total_exec))

    profile_dict = {}
    profile_dict["start_main"] = start_main
    profile_dict["start_agent"] = start_agent
    profile_dict["agent_setup"] = agent_setup
    profile_dict["file_trans"] = file_trans
    profile_dict["main_exec"] = main_exec
    profile_dict["total_exec"] = total_exec

    return profile_dict


def format_var(var):

    return format(var, '.2f')


def write_to_file(string, filename):

    with open(filename, 'a') as file:
        file.write(string)

def print_profile(profile_dict, agent):

    start_main = format_var(profile_dict["start_main"])
    start_agent = format_var(profile_dict["start_agent"])
    agent_setup = format_var(profile_dict["agent_setup"])
    file_trans = format_var(profile_dict["file_trans"])
    main_exec = format_var(profile_dict["main_exec"])
    total_exec = format_var(profile_dict["total_exec"])
    overhead = format_var(profile_dict["overhead"])

    
    write_to_file(f"{agent}\t\t\t{start_main}\t\t{start_agent}\t\t{agent_setup}\t\t{file_trans}\t\t{main_exec}\t\t{total_exec}\t\t{overhead}\n", output_filename)


java = "/usr/lib/jvm/java-11-openjdk-amd64/bin/java"
file_encoding = "-Dfile.encoding=UTF-8"
agent_classpath = "-classpath /home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-shaded.jar"
app_classpath = f"{agent_classpath}:/scratch/pamusuo/repos/dacapobench/benchmarks/dacapo-evaluation-git-704d3251.jar"
# app_name = "com.anonuser.company.apps.AllPermissionsApp"
app_name = "Harness"
app_args_list = ["avrora", "batik", "biojava", "eclipse", "fop", "graphchi", "h2", "kafka", "luindex", "lusearch", "pmd", "spring", "sunflow", "tomcat", "tradebeans", "tradesoap", "xalan", "zxing"]

write_to_file(f"{'Agent'.ljust(22)}\t\t\tstart-main\tstart-agent\tagent-setup\tfile-trans\tmain-exec\ttotal-exec\toverhead\n\n", output_filename)

num_args = len(app_args_list)
count = 0

for app_args in app_args_list:

    count += 1

    print(f"Running {app_args} ({count}/{num_args})")


    write_to_file(f"Running {app_args} ({count}/{num_args})", log_filename)

    write_to_file(f"\nRunning {app_args}:\n", output_filename)

    mon_output_file = f"/home/pamusuo/research/permissions-manager/repos/PPMProfiler/policy_files_0321b/policy_{app_args}.json"
    min_policy_file = f"/home/pamusuo/research/permissions-manager/repos/PPMProfiler/policy_files_0321b/policy_{app_args}_min.json"

    emptyAgentPath = ["empty-perm-agent".ljust(22), "-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-empty-perm-agent.jar=permFilePath=/home/pamusuo/research/permissions-manager/repos/dacapobench/benchmarks/transitive-dependencies.json"]
    filePermAgentPath = ["no-fr-perm-agent".ljust(22), "-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-file-perm-agent.jar"]
    noTransAgentPath = ["no-trans-agent".ljust(22), "-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-no-trans-agent.jar"]
    permAgentPath = ["perm-mon-agent".ljust(22), f"-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar=mode=MONITOR,outputFile={mon_output_file}"]
    permAgentPath2 = ["perm-enf-f-agent".ljust(22), f"-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar=permFilePath={min_policy_file},granularity=fine"]
    permAgentPath3 = ["perm-enf-f-cache-agent".ljust(22), f"-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar=permFilePath={min_policy_file},granularity=fine,enableCache=true"]
    permAgentPath4 = ["perm-enf-c-agent".ljust(22), f"-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar=permFilePath={min_policy_file},granularity=coarse"]
    permAgentPath5 = ["perm-enf-c-cache-agent".ljust(22), f"-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar=permFilePath={min_policy_file},granularity=coarse,enableCache=true"]
    permAgentPath6 = ["perm-both-agent".ljust(22), f"-javaagent:/home/pamusuo/research/permissions-manager/PackagePermissionsManager/target/PackagePermissionsManager-1.0-SNAPSHOT-perm-agent.jar=permFilePath={min_policy_file},granularity=fine,mode=BOTH"]
    permJsm = ["perm-jsm".ljust(22), f"-Djava.security.manager -Djava.security.policy=/scratch/pamusuo/repos/dacapobench/jsm_policy/{app_args}.policy"]
    noAgentPath = ["no-agent".ljust(22), ""]

    agents = [noAgentPath, permAgentPath, permAgentPath2, permAgentPath3, permAgentPath4, permAgentPath5, permAgentPath6, permJsm]

    random.shuffle(agents)

    try:

        if policy_generation:

            command = f"{java} {permAgentPath[1]} {file_encoding} {app_classpath} {app_name} {app_args}"

            run_shell_command(command)
                        
            minimize_command = f"python3 /home/pamusuo/research/permissions-manager/PackagePermissionsManager/evaluation-scripts/minimizePaths.py {mon_output_file} {min_policy_file}"
            execute_shell_command(minimize_command)
            
        else:

            ag_count = 0
            baseline = 0

            app_profiles = {}

            command = f"{java} {noAgentPath[1]} {file_encoding} {app_classpath} {app_name} {app_args} -C"

            run_shell_command(command)
            run_shell_command(command)

            for agent in agents:

                try:

                    ag_count += 1

                    command = f"{java} {agent[1]} {file_encoding} {app_classpath} {app_name} {app_args} --no-validation -C"
                    
                    profiles = []

                    for _ in range(3):
                        profile = run_shell_command(command)
                        processed_profile = process_profile(profile)
                        profiles.append(processed_profile)

                    average_profile = calculate_average_profile(profiles)

                    app_profiles[agent[0]] = average_profile

                    # if (ag_count == 4):
                    #     # Execute minimize.py to generate the minimal policy file
                    #     minimize_command = f"python3 /home/pamusuo/research/permissions-manager/PackagePermissionsManager/evaluation-scripts/minimizePaths.py {mon_output_file} {min_policy_file}"
                    #     execute_shell_command(minimize_command)

                except Exception as e:
                    print(f"An error occurred executing {agent} on {app_args}: {str(e)}\n")
                    write_to_file(f"An error occurred executing {agent} on {app_args}: {str(e)}\n", output_filename)

            # Compute agent's overhead with respect to baseline
            baseline = app_profiles.get(noAgentPath[0], {}).get("main_exec", 0)
            if baseline == 0:
                write_to_file(f"An error occurred calculating overheads for {app_args}: No baseline\n", output_filename)
                baseline = 1

            for agent, average_profile in app_profiles.items():
                overhead = (average_profile["main_exec"] - baseline) / baseline * 100
                average_profile["overhead"] = overhead
                print_profile(average_profile, agent)


    except Exception as e:
        print("An error occurred", str(e))
        write_to_file(f"An error occurred. {str(e)}", output_filename)

class ProfileKey(Enum):
    AGENT_CALLED = 1
    AGENT_EXITING = 2
    FILE_TRANSFORMER_CALLED = 3
    FILE_TRANSFORMER_EXITING = 4
    MAIN_CALLED = 5
    MAIN_EXITING = 6
    PROGRAM_START = 7
    PROGRAM_END = 8
    APP_CALLED = 9
    APP_EXITING = 10