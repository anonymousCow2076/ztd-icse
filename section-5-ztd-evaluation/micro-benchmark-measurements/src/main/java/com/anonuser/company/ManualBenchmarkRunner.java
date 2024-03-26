package com.anonuser.company;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManualBenchmarkRunner {

    public static void main(String[] args) {

        try {

           PermissionsManager.setup();

           // measureDefaultMetrics();

            measureSetupPerformance();

            measureCheckPermissionsPerformance();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void measureSetupPerformance() throws IOException {
        String[] permFiles = new String []{"/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_0.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_1.json",
                "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_3.json",
                "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_5.json",
                "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_10.json",
                "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_15.json",
                "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_20.json",
                "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_30.json",
                "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_40.json"
        };

        PermissionsManagerBenchmarks permissionsManagerBenchmarks = new PermissionsManagerBenchmarks();

        System.out.println("Discarding");
        measureBenchmark(() -> permissionsManagerBenchmarks.measurePermissionsSetupWithFile(permFiles[0]), "PermissionsManagerBenchmarks.discard", 10000);
        measureBenchmark(() -> permissionsManagerBenchmarks.measurePermissionsSetupWithFile(permFiles[0]), "PermissionsManagerBenchmarks.discard", 10000);


        for (String permFile: permFiles) {
            System.out.println("Using " + permFile);
            measureBenchmark(() -> permissionsManagerBenchmarks.measurePermissionsSetupWithFile(permFile), "PermissionsManagerBenchmarks.measurePermissionsSetup", 10000);
        }
    }

    private static void measureCheckPermissionsPerformance() throws IOException {
        String[] permFiles = new String []{"/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_0.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_1.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_3.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_5.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_10.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_15.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_20.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_30.json",
            "/usr/local/company/home/pamusuo/Research/PPMProfiler/permission_files/permission_file_40.json"
        };

        PermissionsManagerBenchmarks permissionsManagerBenchmarks = new PermissionsManagerBenchmarks();

        Set<String> preSubjects = getMockSubjectPath(5);

        System.out.println("Discarding");
        measureBenchmark(() -> permissionsManagerBenchmarks.measurePermissionsCheckWithSubjects(preSubjects), "PermissionsManagerBenchmarks.measurePermissionsCheckWithSubjects", 10000);
        measureBenchmark(() -> permissionsManagerBenchmarks.measurePermissionsCheckWithSubjects(preSubjects), "PermissionsManagerBenchmarks.measurePermissionsCheckWithSubjects", 10000);
        measureBenchmark(() -> permissionsManagerBenchmarks.measurePermissionsCheckWithSubjects(preSubjects), "PermissionsManagerBenchmarks.measurePermissionsCheckWithSubjects", 10000);

        for (String permFile: permFiles) {
            System.out.println("Using " + permFile);
            int[] mockCases = new int[]{0, 5, 10, 15, 20};
            PermissionsManager.setup(permFile, null);

            for (int mockCase: mockCases) {
                System.out.println("Using " + mockCase + " subjects");
                Set<String> subjects = getMockSubjectPath(mockCase);
//            permissionsManagerBenchmarks.measurePermissionsCheckWithSubjects(subjects);
                measureBenchmark(() -> permissionsManagerBenchmarks.measurePermissionsCheckWithSubjects(subjects), "PermissionsManagerBenchmarks.measurePermissionsCheckWithSubjects", 10000);
            }
        }



    }


    private static void measureDefaultMetrics() throws IOException {
        FileBenchmarks fileBenchmarks = new FileBenchmarks();
        SocketBenchmarks socketBenchmarks = new SocketBenchmarks();
        ProcessBuilderBenchmarks processBuilderBenchmarks = new ProcessBuilderBenchmarks();
        PermissionsManagerBenchmarks permissionsManagerBenchmarks = new PermissionsManagerBenchmarks();

        System.out.println("Method name\t\t\t\tAverage");

        measureBenchmark(fileBenchmarks::measureFileInputStream, "FileBenchmarks.measureFileInputStream", 100000);
        measureBenchmark(fileBenchmarks::measureFileOutputStream, "FileBenchmarks.measureFileOutputStream", 10000);
        measureBenchmark(fileBenchmarks::measureFileRead, "FileBenchmarks.measureFileRead", 100000);
        measureBenchmark(fileBenchmarks::measureFileWrite, "FileBenchmarks.measureFileWrite", 10000);
        measureBenchmark(socketBenchmarks::measureSocketConnect, "SocketBenchmarks.measureSocketConnect", 10000);
        measureBenchmark(processBuilderBenchmarks::measureExec, "ProcessBuilderBenchmarks.measureExec", 10000);
        measureBenchmark(permissionsManagerBenchmarks::measurePermissionsSetup, "PermissionsManagerBenchmarks.measurePermissionsSetup", 10000);
        measureBenchmark(permissionsManagerBenchmarks::measurePermissionsCheck, "PermissionsManagerBenchmarks.measurePermissionsCheck", 10000);
        measureBenchmark(permissionsManagerBenchmarks::measurePermissionsCheckReflection, "PermissionsManagerBenchmarks.measurePermissionsCheckReflection", 10000);
    }

    private static void measureBenchmark(Runnable benchmarkMethod, String benchmarkName, int iterations) throws IOException {

        long[] durations = new long[3];

        for (int test = 0; test < 3; test++) {
            for (int i = 0; i < iterations; i++) {
                benchmarkMethod.run();
            }

            long start = System.nanoTime();

            for (int i = 0; i < iterations; i++) {
                benchmarkMethod.run();
            }

            long stop = System.nanoTime();

            long duration = (stop - start)/iterations;

            durations[test] = duration;
        }

        DecimalFormat decimalFormat = new DecimalFormat("0.00");

        double average = calculateAverage(durations);
        double error = calculateError(durations, average);

        System.out.println("[BENCHMARK] " + benchmarkName + " " + decimalFormat.format(average / 1000.0) + "+-" + decimalFormat.format(error / 1000.0) + "us");
    }

    private static double calculateAverage(long[] durations) {
        long sum = 0;
        for (long duration : durations) {
            sum += duration;
        }
        return (double) sum / durations.length;
    }

    private static double calculateError(long[] durations, double average) {
        long minDuration = durations[0];
        long maxDuration = durations[0];
        for (int i = 1; i < durations.length; i++) {
            minDuration = Math.min(minDuration, durations[i]);
            maxDuration = Math.max(maxDuration, durations[i]);
        }
        double lowerBound = average - minDuration;
        double upperBound = maxDuration - average;
        return Math.max(lowerBound, upperBound);
    }

    private static Set<String> getMockSubjectPath(int count) {
        List<String> default_paths = new ArrayList<>();
        // default_paths.add("com.anonuser.company");
        // default_paths.add("org.apache.tomcat");
        default_paths.add("org.apache.commons");

        Set<String> mockPaths = new HashSet<>();
        for (String path: default_paths) {
            mockPaths.add(path + ".TestClass");

            for (int i = 0; i < count - 1; i++) {
                String classname = path + "_" + i + ".TestClass";
                mockPaths.add(classname);
            }
        }

        return mockPaths;

    }

}
