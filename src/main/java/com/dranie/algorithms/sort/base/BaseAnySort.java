package com.dranie.algorithms.sort.base;

/**
 * @author dingdong
 * @since 2021/4/30
 */
public interface BaseAnySort<T extends Comparable<T>> {

    /**
     * 对指定数组进行排序
     *
     * @param a 待排序数组
     */
    void sort(T[] a);
}
