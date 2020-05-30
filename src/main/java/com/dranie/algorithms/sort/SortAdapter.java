package com.dranie.algorithms.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * 一些排序要用到的通用方法
 *
 * @author dranfree
 * @since 2020.05.30
 */
@Slf4j
public abstract class SortAdapter implements Sort {

    public static boolean less(int[] a, int i, int j) {
        return a[i] < a[j];
    }
    public static boolean lessEqual(int[] a, int i, int j) {
        return a[i] <= a[j];
    }
    public static void exch(int[] a, int i, int j) {
        // 注意坑：i = j 的时候，相当于自己和自己异或，结果会变成 0。
        if (i == j) return;
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }
}