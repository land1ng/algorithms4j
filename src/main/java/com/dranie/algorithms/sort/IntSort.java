package com.dranie.algorithms.sort;

/**
 * 排序接口
 *
 * @author dranfree
 * @since 2020.06.29
 */
@FunctionalInterface
public interface IntSort extends BaseIntSort {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a 待排序数组
     */
    default void sort(int[] a) {
        if (a == null || a.length < 2) {
            return; // 没有排序的必要
        }
        sort(a, 0, a.length - 1);
    }

    /**
     * 排序 [lo, hi] 范围内的数据
     *
     * @param a  待排序数组
     * @param lo 左边界
     * @param hi 右边界
     */
    void sort(int[] a, int lo, int hi);
}