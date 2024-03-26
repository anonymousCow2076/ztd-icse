package com.anonuser;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnit_CVE_2023_26119 {

    public  static  void  main (String[] args)  throws Exception {

        // Executes the exploit in 2.xml.
        // Serve htmlunit.html file using a http server

        System.out.println("Recreating CVE-2023-26119");

        try ( final  WebClient  webClient  =  new  WebClient ()) { // no you have a running browser and you can start doing real things // like going to a web page



            final  HtmlPage  page  = webClient.getPage( "http://0.0.0.0:7000/htmlunit.html" );


        }
    }
}
