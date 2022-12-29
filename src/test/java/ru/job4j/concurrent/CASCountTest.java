package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CASCountTest {

    @Test
    public void whenExecute2ThreadThen200() throws InterruptedException {
        var count = new CASCount();
        var first = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                count.increment();
            }
        });
        var second = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                count.increment();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get()).isEqualTo(200);
    }
}