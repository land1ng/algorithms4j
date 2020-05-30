package com.dranie.algorithms.sort.benchmark;

/**
 * @author dranfree
 * @since 2020.05.31
 */
public abstract class SortUtil {

    public static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    public static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a, i, i - 1))
                return false;
        }
        return true;
    }


    // 元素比较
    public static boolean less(int[] a, int i, int j) {
        return a[i] < a[j];
    }
    public static boolean lessEqual(int[] a, int i, int j) {
        return a[i] <= a[j];
    }
    // 元素交换
    public static void exch(int[] a, int i, int j) {
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }
}