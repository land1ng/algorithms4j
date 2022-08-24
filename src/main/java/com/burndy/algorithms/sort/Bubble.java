package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.base.IntSort;
import com.burndy.algorithms.sort.utils.BenchmarkUtil;
import com.burndy.algorithms.utils.Optimizable;
import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡排序：从左往右遍历，相邻元素比较，较大元素往上冒。
 *
 * @author dranfree
 * @since 2020.05.31
 */
@Slf4j
@Optimizable
public class Bubble implements CompareBasedSort, IntSort {

    @Override
    public String name() {
        return "冒泡排序";
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
        int count = 0;
        for (int i = hi; i > lo; i--) {
            for (int j = lo; j < i; j++) {
                if (less(a, j + 1, j)) exch(a, j + 1, j);
                count++;
            }
        }
        log.debug("循环次数：{}", count);
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new Bubble());
        BenchmarkUtil.benchmark(new Bubble());
    }
}