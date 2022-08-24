package com.burndy.algorithms.bst;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
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

    void clear();

    void delete(K key);

    void deleteMin();

    void deleteMax();

    boolean containsKey(K key);

    boolean containsValue(Object value);

    interface Node<K, V> {

        K getKey();

        V getValue();
    }

    default Optional<V> optional(K key) {
        return Optional.ofNullable(get(key));
    }

    default V defaultIfNull(K key, V defaultValue) {
        return optional(key).orElse(defaultValue);
    }

    default V compute(K key,
                      BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);
        V newValue = remappingFunction.apply(key, oldValue);
        if (newValue == null) {
            // delete mapping
            if (oldValue != null || containsKey(key)) {
                // something to remove
                delete(key);
            }  // nothing to do. Leave things as they were.
            return null;
        } else {
            // add or replace old mapping
            put(key, newValue);
            return newValue;
        }
    }

    default V computeIfAbsent(K key,
                              Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V value;
        if ((value = get(key)) == null) {
            value = mappingFunction.apply(key);
            put(key, value);
        }
        return value;
    }

    default V computeIfPresent(K key,
                               BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue;
        if ((oldValue = get(key)) != null) {
            V newValue = remappingFunction.apply(key, oldValue);
            if (newValue != null) {
                put(key, newValue);
                return newValue;
            } else {
                delete(key);
                return null;
            }
        } else {
            return null;
        }
    }

    default void forEach(BiConsumer<? super K, ? super V> consumer) {
        forEach(node -> consumer.accept(node.getKey(), node.getValue()));
    }

    default Stream<Node<K, V>> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
