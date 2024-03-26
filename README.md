# ZTD Artifacts

This is the artifact repository for the paper "ZTD-Java: Mitigating Software Supply Chain Vulnerabilities via Zero-Trust Dependencies"

## Directory Structure

| Top-level folder | Second-level folders |
|------------------|----------------------|
|`section-3-feasibility-analysis` | results |
|                               | scripts |
|`section-4-ztd-design`          | helper-scripts |
|                               | src   |
|`section-5-ztd-evaluation`       | jsm-policy-files |
|                               | PermissionsMicroBenchmarks |
|                               | results   |
|                               | scripts   |
|                               |           |

## ZTD Feasibility Analysis ($3)

- [Results](/section-3-feasibility-analysis/results/): This contains the results to the ZTD hypothesis. They are used to populate the tables in section 3 (Table II - IV).
- [Scripts](/section-3-feasibility-analysis/results/): This contains the scripts to analyze maven packages and generate the results to H3 (Table IV).

## ZTD Design ($4)

- [Helper scripts](/section-4-ztd-design/helper-scripts/): This contains the script provided by ZTD-Java to improve the readability of its generated policies ([Policy Minimization](/section-4-ztd-design/helper-scripts/minimizePaths.py)).

- [ZTD-Java Src](/section-4-ztd-design/src/): This contains the source files for the ZTD-Java implementation. The instructions to run it are contained in [ZTD-Java Readme](/section-4-ztd-design/README.md).

## ZTD Evaluation ($5)

- [JSM Policy Files](/section-5-ztd-evaluation/jsm-policy-files/): This contains the manually-created policy files for running the Dacapo applications with the Java Security Manager.
-[Micro Benchmarks](/section-5-ztd-evaluation/micro-benchmark-measurements/): This contains the code that performs microbenchmarks for ZTD-Java's instrumentation. It populates the data in Table VII and Figure 4.
- [Results](/section-5-ztd-evaluation/results/): This contains the results that were used to populate the tables in $5 (Tables VI - VIII and Figure 4). 
- [RQ1 Recreated Vulnerabilities](/section-5-ztd-evaluation/rq1-recreated-vulnerabilities/): This contains sample applications that contains the vulnerabilities reported in Table VI.
- [Scripts](/section-5-ztd-evaluation/scripts/): This contains the scripts that were used to perform the performance measurements (microbenchmarking and profiling measurements) in $5 RQ2.

## Usage
Usage descriptions for each set of scripts are contained in their own subdirectories. In general most scripts require some modification to set input and ouput directories and files, and to set desired options. These are commented at the top of each header file. 
