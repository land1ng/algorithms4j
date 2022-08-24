package com.burndy.algorithms.sort;

/**
 * 最大优先队列
 *
 * @author dranfree
 * @since 2020.05.30
 */
@SuppressWarnings("UnusedReturnValue")
public interface MinPQ<T extends Comparable<T>> extends PQ<T> {

    /**
     * 返回最大的元素
     *
     * @return
     */
    T min();

    /**
     * 删除并返回最小的元素
     *
     * @return min()
     */
    T delMin();

    static <E extends Comparable<E>> MinPQ<E> of() {
        return new MinPQImpl<>();
    }

    static <E extends Comparable<E>> MinPQ<E> of(int size) {
        return new MinPQImpl<>(size);
    }

    static <E extends Comparable<E>> MinPQ<E> of(E[] data) {
        return new MinPQImpl<>(data);
    }
}