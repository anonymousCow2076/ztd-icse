package com.anonuser.company;

import org.openjdk.jmh.annotations.*;

import java.io.*;
import java.util.concurrent.TimeUnit;


@Fork(value = 2)
@Warmup(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class FileBenchmarks {

    String filename = "/usr/local/company/home/pamusuo/Research/PermissionsMicroBenchmarks/src/main/java/com/anonuser/company/testdir/testfile.txt";
    String fileNameWrite = "/usr/local/company/home/pamusuo/Research/PermissionsMicroBenchmarks/src/main/java/com/anonuser/company/testdir/testfilewrite.txt";


//    This method measures the runtime of FileInputStream
    @Benchmark
    public void measureFileInputStream() {

        try {
            FileInputStream fis =
                new FileInputStream(filename);
            fis.close();
        } catch (IOException ex) {
            // Handle the exception or rethrow it if needed
        }

    }

    @Benchmark
    public String measureFileRead() {
        try {
            BufferedReader reader =
                new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            reader.close();
            return line;
        } catch (IOException e) {
            // Handle the exception or rethrow it if needed
            return null; // or any appropriate value
        }
    }

    @Benchmark
    public void measureFileOutputStream() {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            fos.close();
        } catch (IOException e) {
            // Handle the exception or rethrow it if needed
        }
    }

    @Benchmark
    public void measureFileWrite() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameWrite));
            writer.write("Testing");
            writer.close();
        } catch (IOException e) {
            // Handle the exception or rethrow it if needed
        }
    }

}
