package ru.job4j.pool;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParallelIndexSearchTest {
    @Test
    public void whenInArrayLessThenTenElements() {
        assertThat(ParallelIndexSearch.searchIndex(
                new Object[]{3.14, 10, 1000000000000L, 'c'}, 'c')).isEqualTo(3);
    }

    @Test
    public void whenInArrayMoreThenTenElements() {
        assertThat(ParallelIndexSearch.searchIndex(
                new Object[]{3.14, 10, 1000000000000L, 'c', "Vasya", 5.2F, Integer.parseInt("11"),
                        Double.parseDouble("8.55"), 'v', 'c', 'b'}, 'b')).isEqualTo(10);
    }

    //
    @Test
    public void whenNoElementInArray() {
        assertThat(ParallelIndexSearch.searchIndex(
                new Object[]{3.14, 10, 1000000000000L, 'c', "Vasya", 5.2F, Integer.parseInt("11"),
                        Double.parseDouble("8.55"), 'v', 'c', 'b'}, 'a')).isEqualTo(-1);
    }
}