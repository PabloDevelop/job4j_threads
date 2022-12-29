package ru.job4j.cache;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class CacheTest {
    private Cache cache = new Cache();

    @Test
    public void whenAdded() {
        Base base = new Base(1, 1);
        assertThat(cache.add(base)).isTrue();
    }

    @Test
    public void whenDeleted() {
        Base base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        assertThat(cache.add(base)).isTrue();
    }

    @Test
    public void whenUpdateSuccessful() {
        Base base1 = new Base(1, 1);
        Base base2 = base1;
        cache.add(base1);
        assertThat(cache.update(base2)).isTrue();
    }

    @Test
    public void whenUpdateThrowException() {
        Base base = new Base(1, 1);
        cache.add(base);
        Base base1 = base;
        Base base2 = base;
        cache.update(base1);
        assertThatThrownBy(() -> cache.update(base2))
                .isInstanceOf(OptimisticException.class)
                .hasMessage("Versions are not equal");
    }
}