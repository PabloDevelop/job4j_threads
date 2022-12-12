package ru.job4j.concurrent;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                System.out.println("Start " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                System.out.println("Stop " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}