package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;

/**
 * @author dingdong
 * @since 2021/4/29
 */
@FunctionalInterface
public interface BaseIntSort {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a 待排序数组
     */
    void sort(int[] a);

    default String name() {
        return this.getClass().getSimpleName();
    }

    default void benchmark() {
        if (BenchmarkUtil.check(this)) {
            BenchmarkUtil.benchmark(this);
            BenchmarkUtil.fuckingJDK(this);
        }
    }
}
