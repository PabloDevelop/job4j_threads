package ru.job4j.concurrent;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBlockingQueueTest {

    @Test
    public void whenPutFiveElementsThenGetThem() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> List.of(1, 2, 3, 4)
                .forEach(queue::offer));
        Thread consumer = new Thread(() -> {
            queue.poll();
            queue.poll();
            queue.poll();
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.poll()).isEqualTo(4);
    }
}