package com.dranie.algorithms.sort;

/**
 * 优先队列
 *
 * @author dranfree
 * @since 2020.05.30
 */
public interface PQ<T extends Comparable<T>> {
    /**
     * 向队列中插入一个元素
     *
     * @param element
     */
    void    insert(T element);

    boolean isEmpty();

    int     size();
}