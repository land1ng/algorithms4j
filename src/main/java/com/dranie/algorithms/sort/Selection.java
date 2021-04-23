package com.dranie.algorithms.sort;

/**
 * 选择排序：选择右边最小的元素
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class Selection implements CompareBasedSort {

    @Override
    public String name() {
        return "选择排序";
    }

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int k = i; // 记录右边最小的元素的索引
            for (int j = i + 1; j < a.length; j++) {
                if (less(a, j, i))
                    k = j;
            }
            if (k != i)
                exch(a, i, k);
        }
    }
}