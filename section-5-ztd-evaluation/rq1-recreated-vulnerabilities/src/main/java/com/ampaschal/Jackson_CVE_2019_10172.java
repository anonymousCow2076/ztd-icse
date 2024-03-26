package com.anonuser;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.anonuser.supporting.Person;
import com.anonuser.supporting.VulnDomDeserializer;

public class Jackson_CVE_2019_10172 {
    
    public static void main(String args[]) {
        System.out.println("Recreating CVE-2019-10172");

        // This pulls data from the githubusercontent domain and prints it

        VulnDomDeserializer deserializer = new VulnDomDeserializer();
        try {
            // InputStream is = deserializer.getResource("exploit-2.xml");
            FileInputStream is = new FileInputStream("src/main/files/exploit-2.xml");
            Person person = deserializer._deserialize(IOUtils.toString(is, "UTF-8"), null);
            System.out.println(person.name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
