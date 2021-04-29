package com.dranie.algorithms.sort.base;

/**
 * 基础排序接口
 *
 * @author dingdong
 * @since 2021/4/27
 */
@FunctionalInterface
public interface AnySort<T extends Comparable<T>> {

    /**
     * 对指定数组进行排序
     *
     * @param a 待排序数组
     */
    default void sort(T[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 对指定区间的数据进行排序（[lo, hi]）
     *
     * @param a  待排序数组
     * @param lo 排序左边界
     * @param hi 排序右边界
     */
    void sort(T[] a, int lo, int hi);

    default String name() {
        return this.getClass().getSimpleName();
    }
}
