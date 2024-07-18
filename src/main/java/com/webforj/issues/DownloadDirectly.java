package com.webforj.issues;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class DownloadDirectly {

    public static void main(String[] args) {
        String message = "Message to download";
        Path tempFilePath = null;
        try {
            tempFilePath = Files.createTempFile(null, null);
            Files.write(tempFilePath, message.getBytes(), StandardOpenOption.WRITE);
            System.out.println(tempFilePath.toFile().getAbsolutePath());
            FileReader fr = new FileReader(tempFilePath.toFile());
            BufferedReader reader = new BufferedReader(fr);
            reader.lines().forEach(System.out::print);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(tempFilePath != null) {
                try {
                    Files.delete(tempFilePath);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
