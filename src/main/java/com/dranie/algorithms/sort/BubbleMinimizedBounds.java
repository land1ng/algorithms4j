package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 优化二：判断从某一位置开始已经有序，无需参加下一次循环的遍历，所以遍历范围。
 *
 * 对于有些特殊数组，这种优化会快一点。
 *
 * @author dranfree
 * @since 2020.05.31
 */
@Slf4j
public class BubbleMinimizedBounds extends Bubble {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        int count = 0;
        for (int i = a.length - 1; i > 0; i--) {
            int up = 0;
            for (int j = 0; j < i; j++) {
                count++;
                if (less(a, j + 1, j)) {
                    exch(a, j + 1, j);
                    up = j + 1;
                }
            }
            i = up;
        }
        log.debug("循环次数：{}", count);
    }

    public static void main(String[] args) {
        int[] a = {12, 4, 54, 54, 54, 3, 41, 1, 1, 1, 1, 99, 100, 101, 102};
        BenchmarkUtil.benchmark(a,
                new Bubble(),
                new BubbleEarlyTerminated(),
                new BubbleMinimizedBounds());
//        BenchmarkUtil.check(new BubbleMinimizedBounds());
//        BenchmarkUtil.benchmark(10000,
//                new Bubble(),
//                new BubbleEarlyTerminated(),
//                new BubbleMinimizedBounds());
    }
}