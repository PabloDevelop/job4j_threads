package ru.job4j.concurrent;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int testValue = count.get();
        if (!count.compareAndSet(testValue, testValue++)) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        count.compareAndSet(testValue, testValue++);
    }

    public int get() {
        return count.getAcquire();
    }
}