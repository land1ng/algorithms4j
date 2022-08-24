package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.utils.BenchmarkUtil;
import com.burndy.algorithms.utils.Optimizable;
import lombok.extern.slf4j.Slf4j;

/**
 * 优化一：判断数组已经有序，提前结束循环。
 *
 * @author dranfree
 * @since 2020.05.31
 */
@Slf4j
@Optimizable
public class BubbleEarlyTerminated extends Bubble {

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
            boolean sorted = true;
            for (int j = lo; j < i; j++) {
                count++;
                if (less(a, j + 1, j)) {
                    exch(a, j + 1, j);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
        log.debug("循环次数：{}", count);
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new BubbleEarlyTerminated());
        BenchmarkUtil.benchmark(10000,
                new Bubble(),
                new BubbleEarlyTerminated());
    }
}