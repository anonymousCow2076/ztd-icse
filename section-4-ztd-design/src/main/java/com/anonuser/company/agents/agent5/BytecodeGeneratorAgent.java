package com.anonuser.company.agents.agent5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

import com.anonuser.company.PermissionsManager;
import com.anonuser.company.TestHelper;
import com.anonuser.company.entities.PermissionArgs;
import com.anonuser.company.enums.ProfileKey;
import com.anonuser.company.transformers.BytecodeGenerator;
import com.anonuser.company.utils.Utils;

public class BytecodeGeneratorAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        TestHelper.logTime(ProfileKey.AGENT_CALLED);

        System.out.println("Permissions Agent");
        

        inst.addTransformer(new BytecodeGenerator(), true);

        try {
            inst.retransformClasses(FileInputStream.class, FileOutputStream.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }

        TestHelper.logTime(ProfileKey.AGENT_EXITING);

    }
}
