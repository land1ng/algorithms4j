package com.dranie.algorithms.bst;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author ran.ding
 * @since 2021/7/29
 */
public interface Tree<K extends Comparable<K>, V> extends Iterable<Tree.Node<K, V>> {

    /**
     * find value by key, or else null.
     *
     * @param key ~
     * @return ~
     */
    V get(K key);

    /**
     * set k-v, and return old value if present, or else return null.
     *
     * @param key   ~
     * @param value ~
     * @return ~
     */
    V put(K key, V value);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    K minKey();

    K maxKey();

    void deleteMin();

    void deleteMax();

    void deleteKey(K key);

    boolean containsKey(K key);

    boolean containsValue(Object value);

    interface Node<K, V> {

        K getKey();

        V getValue();
    }

    default Stream<Node<K, V>> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
