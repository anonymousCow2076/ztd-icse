package com.anonuser.company;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Fork(value = 2)
@Warmup(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class ProcessBuilderBenchmarks {

    @Benchmark
    public void measureExec() {
        // Command to execute
        String command = "ls -l";

        // Create the runtime instance
        Runtime runtime = Runtime.getRuntime();

        try {
            // Start the process
            runtime.exec(command);
        } catch (IOException ex) {
            // Handle
        }

    }
}
