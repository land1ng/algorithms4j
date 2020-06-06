package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.benchmark.BenchmarkUtil;
import com.dranie.algorithms.utils.Optimizable;
import lombok.extern.slf4j.Slf4j;

/**
 * 冒泡排序：从左往右遍历，相邻元素比较，较大元素往上冒。
 *
 * @author dranfree
 * @since 2020.05.31
 */
@Slf4j
@Optimizable
public class Bubble implements Sort {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        int count = 0;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (less(a, j + 1, j))
                    exch(a, j + 1, j);
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