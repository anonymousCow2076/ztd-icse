# Minimize
By default Next-JSM permissions models when run in trace mode output the full paths to any accessed File I/O operations.
This utility is intended to help condense these autogenerated paths in order to aid readability when looking through the outputted permission files.

## Usage
To use the minimization tool run
`python3 minimizePaths.py inputJsonPath outputJsonPath`