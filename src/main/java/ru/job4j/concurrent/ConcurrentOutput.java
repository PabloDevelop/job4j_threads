package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread another1 = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        another1.start();
        System.out.println(Thread.currentThread().getName());
    }
}
