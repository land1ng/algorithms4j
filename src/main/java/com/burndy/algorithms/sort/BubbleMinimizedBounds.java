package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.utils.BenchmarkUtil;
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
            int up = 0;
            for (int j = lo; j < i; j++) {
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