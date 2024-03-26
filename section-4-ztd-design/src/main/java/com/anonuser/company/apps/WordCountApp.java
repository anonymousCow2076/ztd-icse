package com.anonuser.company.apps;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.anonuser.company.TestHelper;
import com.anonuser.company.enums.ProfileKey;

public class WordCountApp {
    public static void main(String[] args) {

        TestHelper.logTime(ProfileKey.MAIN_CALLED);

        performFileCount();

        TestHelper.logTime(ProfileKey.MAIN_EXITING);
    }

    public static void performFileCount() {
        String fileName = "src/main/java/com/anonuser/company/test.txt";

        try {
            int wordCount = countWords(fileName);
            System.out.println("Number of words in the file: " + wordCount);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static int countWords(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int wordCount = 0;

        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            wordCount += words.length;
        }

        reader.close();
        return wordCount;

    }
}
