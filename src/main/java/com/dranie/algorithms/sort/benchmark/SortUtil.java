package com.dranie.algorithms.sort.benchmark;

import com.dranie.algorithms.utils.Assert;

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

    public static boolean isSorted(double[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    public static boolean isSorted(double[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a, i, i - 1))
                return false;
        }
        return true;
    }

    @SuppressWarnings({"rawtypes"})
    public static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }
    @SuppressWarnings({"rawtypes"})
    public static boolean isSorted(Comparable[] a, int lo, int hi) {
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
    public static boolean less(double[] a, int i, int j) {
        return a[i] < a[j];
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean less(Comparable[] a, int i, int j) {
        Assert.notNull(a[i]);
        return a[i].compareTo(a[j]) < 0;
    }

    public static boolean lessEqual(int[] a, int i, int j) {
        return a[i] <= a[j];
    }
    public static boolean lessEqual(double[] a, int i, int j) {
        return a[i] <= a[j];
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean lessEqual(Comparable[] a, int i, int j) {
        Assert.notNull(a[i]);
        return a[i].compareTo(a[j]) <= 0;
    }

    // 元素交换
    public static void exch(int[] a, int i, int j) {
        if (i == j)
            return;
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }
    public static void exch(double[] a, int i, int j) {
        double tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    @SuppressWarnings({"rawtypes"})
    public static void exch(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}