package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public Future<?> emailTo(User user) {
        return pool.submit(() -> send(getSubject(user), getBody(user), user.getEmail()));
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

    private static String getSubject(User user) {
        return String.format("Notification %s to email %s", user.getName(), user.getEmail());
    }

    private static String getBody(User user) {
        return String.format("Add a new event to %s", user.getName());
    }
}