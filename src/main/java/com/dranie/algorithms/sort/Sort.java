package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;

/**
 * 排序接口
 *
 * @author dranfree
 * @since 2020.05.30
 */
@FunctionalInterface
public interface Sort {
    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    void sort(int[] a);


    default boolean less(int[] a, int i, int j) {
        return a[i] < a[j];
    }
    default boolean lessEqual(int[] a, int i, int j) {
        return a[i] <= a[j];
    }
    default void exch(int[] a, int i, int j) {
        // 注意坑：i = j 的时候，相当于自己和自己异或，结果会变成 0。
        if (i == j)
            return;
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }

    default void benchmark() {
        if (!BenchmarkUtil.check(this)) {
            BenchmarkUtil.logCheckFailed(this);
        } else {
            BenchmarkUtil.benchmark(this);
            BenchmarkUtil.fuckingJDK(this);
        }
    }
}