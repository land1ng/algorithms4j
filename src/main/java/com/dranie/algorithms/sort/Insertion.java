package com.dranie.algorithms.sort;

/**
 * @author dranfree
 * @since 2020.05.30
 */
public class Insertion extends SortAdapter {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    /**
     * 排序指定的子数组部分
     *
     * @param a
     * @param lo
     * @param hi
     */
    public static void sort(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                if (less(a, j, j - 1)) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}