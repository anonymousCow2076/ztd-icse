package com.anonuser.company;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
//        org.openjdk.jmh.Main.main(args);

        Options opt = new OptionsBuilder()
//                .include(PermissionsManagerBenchmarks.class.getSimpleName())
//                .forks(2)
                .build();

        new Runner(opt).run();

    }
}
