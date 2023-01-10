package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch extends RecursiveTask<Integer> {
    private final static int ARRAY_LENGTH_TO_DO_LINEAR = 10;
    private final int from;
    private final int to;
    private final Object[] array;
    private final Object objectToSearch;

    public ParallelIndexSearch(Object[] array, int from, int to, Object objectToSearch) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.objectToSearch = objectToSearch;
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
        int rsl;
        if (to - from <= ARRAY_LENGTH_TO_DO_LINEAR) {
            rsl = search();
        } else {
            int mid = (to + from) >>> 1;
            ParallelIndexSearch searchLeft = new ParallelIndexSearch(array, from,
                    mid, objectToSearch);
            ParallelIndexSearch searchRight = new ParallelIndexSearch(array, mid + 1,
                    to, objectToSearch);
            searchLeft.fork();
            searchRight.fork();
            rsl = searchLeft.join() > 0 ? searchLeft.join() : searchRight.join();
        }
        return rsl;
    }
}
