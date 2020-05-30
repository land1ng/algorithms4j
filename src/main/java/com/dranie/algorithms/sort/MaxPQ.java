package com.dranie.algorithms.sort;

/**
 * 最大优先队列
 *
 * @author dranfree
 * @since 2020.05.30
 */
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
     * @return
     */
    T delMax();

}