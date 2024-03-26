# Static Analysis of Maven Packages
This is a collection of scripts and utilities built around codeql designed to statically analyze Maven Packages looking for OS system calls. 

## Scripts
- fetch\_top\_repo.py: This is used to create a file of the top maven packages within the Ecosystems database, alongside the URL their repositories can be cloned from
- staticAnalysis.py: This is used to run the analysis in conjunction with custom codeql queries. It takes in the output from fetch\_top\_repo.py runs a static analysis on each package listed and produces an output file detailing the system calls detected from each package
- summarizeStaticAnalysis.py: This is used to create a simple summary of the results generated from running staticAnalysis.py it will report on the number of packages that used each system call, and summary statistics for each category.

## Usage
All relevant options can be set in the first few lines of each of the scripts, this generally includes input and output locations as well as locations for necessary external sources such as codeql and the custom queries you wish to run. All scripts were tested using Python 3.10.12

## Additional Files
There are two additional text files in this directory, these can be directly inputted into the staticAnalysis.py script to demonstrate basic functionality on a set of well know packages.
