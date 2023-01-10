package ru.job4j.pool;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelIndexSearchTest {
    @Test
    public void whenInArrayLessThenTenElements() {
        ParallelIndexSearch parallelIndexSearch = new ParallelIndexSearch(
                new Object[]{3.14, 10, 1000000000000L, 'c'}, 0, 4, 'c');
        assertThat(parallelIndexSearch.invoke()).isEqualTo(3);
    }

    @Test
    public void whenInArrayMoreThenTenElements() {
        ParallelIndexSearch parallelIndexSearch = new ParallelIndexSearch(
                new Object[]{3.14, 10, 1000000000000L, 'c', "Vasya", 5.2F, Integer.parseInt("11"),
                        Double.parseDouble("8.55"), 'v', 'c', 'b'}, 0, 11, 'b');
        assertThat(parallelIndexSearch.invoke()).isEqualTo(10);
    }

    @Test
    public void whenNoElementInArray() {
        ParallelIndexSearch parallelIndexSearch = new ParallelIndexSearch(
                new Object[]{3.14, 10, 1000000000000L, 'c', "Vasya", 5.2F, Integer.parseInt("11"),
                        Double.parseDouble("8.55"), 'v', 'c', 'b'}, 0, 11, 'a');
        assertThat(parallelIndexSearch.invoke()).isEqualTo(-1);
    }
}