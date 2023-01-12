package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final static int ARRAY_LENGTH_TO_DO_LINEAR = 10;
    private final int from;
    private final int to;
    private final T[] array;
    private final T objectToSearch;

    public ParallelIndexSearch(T[] array, int from, int to, T objectToSearch) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.objectToSearch = objectToSearch;
    }

    public static <T> int searchIndex(T[] array, T obj) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length, obj));
    }

    /**
     * Находит индекс первого вхождения элемента.
     *
     * @return
     */
    private int search() {
        int rsl = -1;
        for (int i = from; i < to; i++) {
            if (objectToSearch.equals(array[i])) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    @Override
    protected Integer compute() {
        if (to - from <= ARRAY_LENGTH_TO_DO_LINEAR) {
            return search();
        }
        int mid = (to + from) >>> 1;
        ParallelIndexSearch<T> searchLeft = new ParallelIndexSearch<>(array, from,
                mid, objectToSearch);
        ParallelIndexSearch<T> searchRight = new ParallelIndexSearch<>(array, mid + 1,
                to, objectToSearch);
        searchLeft.fork();
        searchRight.fork();
        return Math.max(searchLeft.join(), searchRight.join());
    }
}