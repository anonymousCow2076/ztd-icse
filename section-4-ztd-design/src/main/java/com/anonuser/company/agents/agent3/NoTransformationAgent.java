package com.anonuser.company.agents.agent3;

import java.lang.instrument.Instrumentation;

import com.anonuser.company.PermissionsManager;
import com.anonuser.company.TestHelper;
import com.anonuser.company.entities.PermissionArgs;
import com.anonuser.company.enums.ProfileKey;
import com.anonuser.company.utils.Utils;

public class NoTransformationAgent {
    public static void premain(String agentArgs, Instrumentation inst) {

        TestHelper.logTime(ProfileKey.AGENT_CALLED);

        System.out.println("No transformation Agent");

        PermissionArgs permissionArgs = Utils.processAgentArgs(agentArgs);

        PermissionsManager.setup(permissionArgs);
        TestHelper.logTime(ProfileKey.AGENT_EXITING);

    }
}
