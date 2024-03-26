package com.anonuser.company.agents.agent1;

import com.anonuser.company.PermissionsManager;
import com.anonuser.company.TestHelper;
import com.anonuser.company.entities.PermissionArgs;
import com.anonuser.company.enums.ProfileKey;
import com.anonuser.company.utils.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.net.Socket;
import java.security.ProtectionDomain;

public class EmptyTransformerPermissionsAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        TestHelper.logTime(ProfileKey.AGENT_CALLED);

        System.out.println("Permissions Agent");

        PermissionArgs permissionArgs = Utils.processAgentArgs(agentArgs);

        PermissionsManager.setup(permissionArgs);

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                return classfileBuffer;
            }
        }, true);

        try {

            inst.retransformClasses(FileInputStream.class, FileOutputStream.class, Socket.class, ProcessBuilder.class, Thread.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }

        TestHelper.logTime(ProfileKey.AGENT_EXITING);


    }
}
