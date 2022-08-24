package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.base.IntSort;
import com.burndy.algorithms.sort.utils.SortUtil;

/**
 * 插入排序的最差情况：反序数组，最好情况：有序数组。
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class Insertion implements CompareBasedSort, IntSort {

    @Override
    public String name() {
        return "插入排序";
    }

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 排序 [lo, hi] 范围内的数据
     *
     * @param a  待排序数组
     * @param lo 左边界
     * @param hi 右边界
     */
    @Override
    public void sort(int[] a, int lo, int hi) {
        sortRange(a, lo, hi);
    }

    public static void sortRange(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                if (SortUtil.less(a, j, j - 1)) {
                    SortUtil.exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}