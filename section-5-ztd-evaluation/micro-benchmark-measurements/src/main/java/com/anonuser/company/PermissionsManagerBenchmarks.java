package com.anonuser.company;

import org.openjdk.jmh.annotations.*;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Fork(value = 2)
@Warmup(iterations = 20, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class PermissionsManagerBenchmarks {


    String filename = "src/main/java/com/anonuser/company/testdir/testfile.txt";

    @Setup
    public void setup() {
        PermissionsManager.setup();
    }

    @Benchmark
    public void measurePermissionsCheck() {
        PermissionsManager.checkPermission(0, 0, filename);
    }

    @Benchmark
    public void measurePermissionsSetup() {
        PermissionsManager.setup();
    }

    public void measurePermissionsSetupWithFile(String permFile) {
        PermissionsManager.setup(permFile, null);
    }

    public void measurePermissionsCheckWithSubjects(Set<String> subjects) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerName = stackTrace[2].getClassName(); // 2 represents the index of the class calling the class's constructor

//        TODO: Does this skip classes loaded by reflection?
        if (!callerName.startsWith("jdk.internal.loader")) {
            try {
                Class<?> permManagerClass = ClassLoader.getSystemClassLoader().loadClass("com.anonuser.company.PermissionsManager");
                Method logMethod = permManagerClass.getMethod("checkPermissionEval", int.class, int.class, String.class, Set.class);
                String name = "src/main/java/com/anonuser/company/testdir/testfile.txt";
                logMethod.invoke(null, 0, 0, filename, subjects);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                System.out.println("Exception thrown in instrumentation");
            }
        }
    }

    @Benchmark
    public void measurePermissionsCheckReflection() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerName = stackTrace[2].getClassName(); // 2 represents the index of the class calling the class's constructor

//        TODO: Does this skip classes loaded by reflection?
        if (!callerName.startsWith("jdk.internal.loader")) {
            try {
                Class<?> permManagerClass = ClassLoader.getSystemClassLoader().loadClass("com.anonuser.company.PermissionsManager");
                Method logMethod = permManagerClass.getMethod("checkPermission", int.class, int.class, String.class);
                String name = "src/main/java/com/anonuser/company/testdir/testfile.txt";
                logMethod.invoke(null, 0, 0, new File(name).getAbsolutePath());
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                System.out.println("Exception thrown in instrumentation");
            }
        }
    }


}
