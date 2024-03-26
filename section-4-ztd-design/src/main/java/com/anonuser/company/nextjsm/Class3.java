package com.anonuser.company.nextjsm;

import java.io.FilePermission;

public class Class3 {

    public static void m() {
        SecurityManager securityManager = System.getSecurityManager();

        securityManager.checkPermission(new FilePermission("/home/pamusuo/research/permissions-manager/PackagePermissionsManager/src/main/java/com/anonuser/company/permfiles/sample-permissions.json", "read"));
    }
}
