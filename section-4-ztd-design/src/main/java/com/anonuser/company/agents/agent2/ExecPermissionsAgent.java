package com.anonuser.company.agents.agent2;

import com.anonuser.company.PermissionsManager;
import com.anonuser.company.TestHelper;
import com.anonuser.company.agents.agent4.PermissionsAgent;
import com.anonuser.company.entities.PermissionArgs;
import com.anonuser.company.entities.TransformProps;
import com.anonuser.company.enums.ProfileKey;
import com.anonuser.company.transformers.PermissionsTransformer;
import com.anonuser.company.utils.Utils;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Map;

public class ExecPermissionsAgent {


    public static void premain(String agentArgs, Instrumentation inst) {

        TestHelper.logTime(ProfileKey.AGENT_CALLED);

        System.out.println("Exec Permissions Agent");

        PermissionArgs permissionArgs = Utils.processAgentArgs(agentArgs);

        PermissionsManager.setup(permissionArgs);

        Map<String, TransformProps> transformPropsMap = PermissionsAgent.getTransformPropMap(false, false, false, true, false);

        inst.addTransformer(new PermissionsTransformer(transformPropsMap, false), true);

        try {
            // We retransform these classes because they are already loaded into the JVM
            inst.retransformClasses(ProcessBuilder.class);
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }

        TestHelper.logTime(ProfileKey.AGENT_EXITING);


    }
}
