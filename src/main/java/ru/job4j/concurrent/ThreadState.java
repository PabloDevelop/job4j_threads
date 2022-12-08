package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        System.out.println(first.getName() + " started");
        System.out.println(second.getName() + " started");
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(first.getName() + " and " + second.getName() + " are terminated.");
    }
}