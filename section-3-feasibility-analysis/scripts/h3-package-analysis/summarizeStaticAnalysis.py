import re
import json
from collections import defaultdict

input_file = "" #Path to raw static analysis data

# Initialize counters
build_failures = 0
clone_failures = 0
successes = 0

# Initialize dictionaries for counting and summarizing
count_dict = defaultdict(int)
summary_stats = defaultdict(lambda: defaultdict(int))

# Define all categories to ensure they are included in the summary
all_categories = ['file-read', 'file-write', 'socket-connect', 'socket-server', 'runtime-exec', 'jndi-lookup', 'jndi-bind', 'object-parse', 'xml-parse', 'threading', 'system-load']

# Process each line of the log file
with open(input_file, "r") as file:
    for line in file:
        line = line.strip()
        
        # Check for build failures
        if "CodeQL build failed" in line:
            build_failures += 1
            
        # Check for clone failures
        elif "Cloning failed" in line:
            clone_failures += 1
            
        # Check for successes and extract stats
        else:
            match = re.search(r"\{(.*?)\}", line)
            if match:
                successes += 1
                # Convert the matched string to a dictionary
                stats_str = "{" + match.group(1) + "}"
                stats_str = stats_str.replace("'", "\"")  # Ensure double quotes for JSON
                try:
                    stats = json.loads(stats_str)
                    # Ensure all categories are updated
                    for category in all_categories:
                        value = stats.get(category, 0)
                        summary_stats[category]['total'] += value
                        summary_stats[category]['count'] += 1  # Increment count for every package, regardless of value
                        if value > 0:
                            count_dict[category] += 1
                except json.JSONDecodeError as e:
                    print(f"Error decoding JSON from line: {line}\nError: {e}")

# Display the tallied results
print(f"Build Failures: {build_failures}")
print(f"Clone Failures: {clone_failures}")
print(f"Successes: {successes}")

# Display the number of packages with >0 in each category
for key, value in count_dict.items():
    print(f"Packages with >0 {key}: {value}")

# Display summary statistics for each category, including packages with 0
for category in all_categories:
    stats = summary_stats[category]
    avg = stats['total'] / stats['count'] if stats['count'] > 0 else 0
    print(f"Summary for {category}: Total = {stats['total']}, Count = {stats['count']}, Average = {avg:.2f}")
