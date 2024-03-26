package com.anonuser.company.apps;

import com.anonuser.company.TestHelper;
import com.anonuser.company.enums.ProfileKey;

public class AllPermissionsApp {

    public static void main(String[] args) {

        TestHelper.logTime(ProfileKey.MAIN_CALLED);

        WordCountApp.performFileCount();
        ReadNetworkApp.performNetworkCount();
        ProcessExecApp.performShellExec();
        FileWriteApp.performFileWriteOperation();
        SocketConnectionApp.performSocketConnection();

        TestHelper.logTime(ProfileKey.MAIN_EXITING);
    }
}


