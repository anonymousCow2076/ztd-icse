package com.anonuser.company;

import org.openjdk.jmh.annotations.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

@Fork(value = 2)
@Warmup(iterations = 10, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class SocketBenchmarks {

    SocketAddress address;

    public SocketBenchmarks() {
        setup();
    }

    @Setup
    public void setup() {
        address = new InetSocketAddress("localhost", 8080);
    }



    @Benchmark
    public void measureSocketConnect() {
        try (Socket socket = new Socket()) {
            socket.connect(address);
        } catch (IOException ex) {
            // Handle
        }
    }
}
