package com.dranie.algorithms.lru;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;

/**
 * @author ran.ding
 * @since 2021/9/27
 */
@RequiredArgsConstructor
class SynchronizedLRU<K, V> implements LRU<K, V> {

    private final LRU<K, V> delegate;

    @Override
    public V get(K key) {
        return delegate.get(key);
    }

    @Override
    public synchronized void add(K key, V val) {
        delegate.add(key, val);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public int capacity() {
        return delegate.capacity();
    }

    @Override
    public boolean containsKey(K key) {
        return delegate.containsKey(key);
    }

    @Override
    public synchronized void clear() {
        delegate.clear();
    }

    @Override
    public synchronized void remove(K key) {
        delegate.remove(key);
    }

    @Override
    public Iterator<Item<K, V>> iterator() {
        return delegate.iterator();
    }
}
