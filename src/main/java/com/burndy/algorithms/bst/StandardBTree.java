package com.burndy.algorithms.bst;

import java.util.Optional;

/**
 * @author ran.ding
 * @since 2021/7/30
 */
public class StandardBTree<K extends Comparable<K>, V> implements MultiwayBST<K, V> {

    @Override
    public Optional<V> get(K key) {
        return Optional.empty();
    }

    @Override
    public Optional<V> put(K key, V val) {
        return Optional.empty();
    }

    @Override
    public Optional<V> delete(K key) {
        return Optional.empty();
    }
}
