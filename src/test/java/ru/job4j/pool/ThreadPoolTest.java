package ru.job4j.pool;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ThreadPoolTest {
    @Test
    public void doFiveTasks() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 1; i < 101; i++) {
            String msg = Thread.currentThread().getName() + " do task " + i;
            threadPool.work(() -> System.out.println(msg));
        }
        threadPool.shutdown();
        assertThat(threadPool.isAlive()).isFalse();
    }
}