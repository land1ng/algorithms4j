package com.dranie.algorithms.sort;

import java.util.Arrays;

/**
 * 选择排序：选择右边最小的元素
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class Selection implements CompareBasedSort, IntSort {

    @Override
    public String name() {
        return "选择排序";
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
        for (int i = lo; i <= hi; i++) {
            int k = i; // 记录右边最小的元素的索引
            for (int j = i + 1; j <= hi; j++) {
                if (less(a, j, k)) k = j;
            }
            if (k != i) exch(a, i, k);
        }
    }

    public static void main(String[] args) {
        int[] a = { 1, 3, 2, 5, 111, -1, 2 };
        new Selection().sort(a);
        System.out.println(Arrays.toString(a));
    }
}