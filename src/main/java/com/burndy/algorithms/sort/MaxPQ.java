package com.burndy.algorithms.sort;

/**
 * 最大优先队列
 *
 * @author dranfree
 * @since 2020.05.30
 */
@SuppressWarnings("UnusedReturnValue")
public interface MaxPQ<T extends Comparable<T>> extends PQ<T> {

    /**
     * 返回最大的元素
     *
     * @return
     */
    T max();

    /**
     * 删除并返回最大的元素
     *
     * @return max()
     */
    T delMax();

    static <E extends Comparable<E>> MaxPQ<E> of() {
        return new MaxPQImpl<>();
    }

    static <E extends Comparable<E>> MaxPQ<E> of(int size) {
        return new MaxPQImpl<>(size);
    }

    static <E extends Comparable<E>> MaxPQ<E> of(E[] data) {
        return new MaxPQImpl<>(data);
    }
}