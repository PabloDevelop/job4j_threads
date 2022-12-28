package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int testValue;
        int incrValue;
        do {
            testValue = count.get();
            incrValue = testValue++;
        } while (!count.compareAndSet(testValue, incrValue));
    }

    public int get() {
        return count.getAcquire();
    }
}