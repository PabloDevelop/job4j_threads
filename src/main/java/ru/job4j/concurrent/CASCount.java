package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        int testValue;
        int incrValue;
        do {
            testValue = count.get();
            incrValue = testValue + 1;
        } while (!count.compareAndSet(testValue, incrValue));
    }

    public int get() {
        return count.get();
    }
}