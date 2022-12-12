package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WgetAt144271 implements Runnable {
    private final String url;
    private final int speed;
    private static final String url_regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public WgetAt144271(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Args length is null.");
        }
        if (!args[0].matches(url_regex)) {
            throw new IllegalArgumentException("URL isn't correct");
        }
        if (Integer.parseInt(args[1]) <= 0) {
            throw new IllegalArgumentException("Speed value isn't correct.");
        }
    }

    /**
     * Засекает время начала скачки данных (long start),
     * проверяет сколько уже скачалось (int bytesDownloaded),
     * если скачалось больше заданной скорости в байтах,
     * то проверяет время затраченное на скачку (long timeDelta).
     * Если время меньше 1 сек, то выставляет задержку (sleep)
     * и зануляет счетчик скаченных байтов.
     */
    @Override
    public void run() {
        String[] urlSplit = url.split("/|\\.");
        String downloadedFile = String.format("%s%s.%s", "Downloaded_", urlSplit[urlSplit.length - 2],
                urlSplit[urlSplit.length - 1]);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(downloadedFile)) {
            long start = System.currentTimeMillis();
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int bytesDownloaded = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                bytesDownloaded += bytesRead;
                if (speed < bytesDownloaded) {
                    long end = System.currentTimeMillis();
                    long timeDelta = end - start;
                    if (timeDelta < 1000) {
                        Thread.sleep(1000 - timeDelta);
                    }
                    bytesDownloaded = 0;
                    start = System.currentTimeMillis();
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        validate(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new WgetAt144271(url, speed));
        wget.start();
        wget.join();
    }
}