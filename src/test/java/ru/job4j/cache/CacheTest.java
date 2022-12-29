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
        Base base = new Base(1, 1);
        cache.add(base);
        Base base1 = base;
        Base base2 = base;
        assertThat(cache.update(base1)).isTrue();
        assertThatThrownBy(() -> cache.update(base2))
                .isInstanceOf(OptimisticException.class)
                .hasMessage("Versions are not equal");
    }
}