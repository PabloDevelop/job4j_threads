package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class RolColSumTest {
    @Test
    void whenUseSyncSumming() {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        RolColSum.Sums[] testArray = {
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18),
        };
        RolColSum.Sums[] resultArray = RolColSum.sum(array);
        assertArrayEquals(testArray, resultArray);
    }

    @Test
    void whenUseAsyncSumming() throws ExecutionException, InterruptedException {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        RolColSum.Sums[] testArray = {
                new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18),
        };
        RolColSum.Sums[] resultArray = RolColSum.asyncSum(array);
        assertArrayEquals(testArray, resultArray);
    }
}