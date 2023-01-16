package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {
    @Test
    void whenUseSyncSumming() {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        Sums[] testArray = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18),
        };
        Sums[] resultArray = RolColSum.sum(array);
        assertThat(testArray).isEqualTo(resultArray);
    }

    @Test
    void whenUseAsyncSumming() throws ExecutionException, InterruptedException {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        };
        Sums[] testArray = {
                new Sums(6, 12),
                new Sums(15, 15),
                new Sums(24, 18),
        };
        Sums[] resultArray = RolColSum.asyncSum(array);
        assertThat(testArray).isEqualTo(resultArray);
    }
}