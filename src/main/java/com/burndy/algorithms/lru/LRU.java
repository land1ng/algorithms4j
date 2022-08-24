package com.burndy.algorithms.lru;

import java.util.Iterator;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * least recently used
 *
 * @author ran.ding
 * @since 2021/9/27
 */
public interface LRU<K, V> extends Iterable<LRU.Item<K, V>> {

    V get(K key);

    default V get(K key, V valueIfMissing) {
        V cache = get(key);
        if (cache == null) {
            cache = valueIfMissing;
            if (cache != null) {
                add(key, cache);
            }
        }
        return cache;
    }

    default V get(K key, Function<K, V> fetcher) {
        V cache = get(key);
        if (cache == null) {
            cache = fetcher.apply(key);
            if (cache != null) {
                add(key, cache);
            }
        }
        return cache;
    }

    void add(K key, V val);

    int size();

    int capacity();

    default boolean isFull() {
        return size() == capacity();
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean containsKey(K key);

    void clear();

    void remove(K key);

    @Override
    Iterator<Item<K, V>> iterator();

    default Iterator<K> keyIterator() {
        Iterator<Item<K, V>> it = iterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public K next() {
                return it.next().key();
            }
        };
    }

    default Iterator<V> valueIterator() {
        Iterator<Item<K, V>> it = iterator();
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public V next() {
                return it.next().value();
            }
        };
    }

    interface Item<K, V> {

        K key();

        V value();
    }

    default Stream<Item<K, V>> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    default Stream<K> keyStream() {
        return StreamSupport.stream(spliterator(), false).map(Item::key);
    }

    default Stream<V> valueStream() {
        return StreamSupport.stream(spliterator(), false).map(Item::value);
    }

    default LRU<K, V> sync() {
        if (this instanceof SynchronizedLRU) {
            return this;
        } else {
            return new SynchronizedLRU<>(this);
        }
    }
}
