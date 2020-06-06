package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;
import com.dranie.algorithms.utils.Optimizable;
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
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        int count = 0;
        for (int i = a.length - 1; i > 0; i--) {
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
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