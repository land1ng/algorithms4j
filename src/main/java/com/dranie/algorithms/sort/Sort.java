package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;

/**
 * 排序接口
 *
 * @author dranfree
 * @since 2020.06.29
 */
@FunctionalInterface
public interface Sort {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    void sort(int[] a);

    default void benchmark() {
        if (BenchmarkUtil.check(this)) {
            BenchmarkUtil.benchmark(this);
            BenchmarkUtil.fuckingJDK(this);
        }
    }
}