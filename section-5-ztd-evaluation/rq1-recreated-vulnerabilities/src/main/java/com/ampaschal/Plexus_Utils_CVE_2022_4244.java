package com.anonuser;

import java.io.File;

import org.codehaus.plexus.util.Expand;

public class Plexus_Utils_CVE_2022_4244 {
    public static void main(String args[]) {
        
        // The file in zip-slip-payload.zip is extracted to a different directory
        System.out.println("Recreating CVE-2022-4244");
        Expand expand = new Expand();
        expand.setSrc(new File("src/main/files/zip-slip-payload.zip"));
        expand.setDest(new File("src/main/files/zip-slip-payload"));
        try {
            expand.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
