package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Инициализация пула должна быть по количеству ядер в системе.
 * Количество нитей всегда одинаковое и равно size.
 * В каждую нить передается блокирующая очередь tasks.
 * В методе run мы должны получить задачу из очереди tasks.
 */
public class ThreadPool extends Thread {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    /**
     * Это блокирующая очередь.
     * Если в очереди нет элементов, то нить переводится в состояние Thread.State.WAITING.
     */
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            ThreadPoolThreads threadPoolThreads = new ThreadPoolThreads();
            threads.add(threadPoolThreads);
            threadPoolThreads.start();
        }
    }

    /**
     * Этот метод должен добавлять задачи в блокирующую очередь tasks.
     *
     * @param job
     */
    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    /**
     * Этот метод завершит все запущенные задачи.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class ThreadPoolThreads extends Thread {
        public void run() {
            Runnable task;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    task = tasks.poll();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}