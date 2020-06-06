package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.benchmark.BenchmarkUtil;

/**
 * 希尔排序：h-sort，从插入排序基础上发展而来。
 *
 * 主要思想：使数组中任意间隔为 h 的元素都是有序的，这样当 h = 1 的时候，整个数组就是有序的了。
 *
 * 性能考究：
 * 原始插入排序，每次最多只能将一个元素往前移动一位，
 * 基于 h-sort 思想，我们可以将元素移动到很远的地方，为实现很小的 h-sort 创造方便。
 *
 * 对于大规模乱序数组，插入排序效率很慢。
 *
 * @author dranfree
 * @since 2020.05.31
 */
public class ShellSort implements Sort {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        int len = a.length;
        int h = 1;
        while (h < len / 3) h = 3 * h + 1;
        while (h > 0) {
            for (int i = h; i < len; i++) {
                // 对于每个间隔为 h 的子数组使用插入排序
                for (int j = i; j >= h && less(a, j, j - h); j -= h)
                    exch(a, j, j - h);
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
//        BenchmarkUtil.check(new ShellSort());
//        BenchmarkUtil.benchmark(new ShellSort());
//        BenchmarkUtil.benchmark(100000,
//                new Insertion(),
//                new ShellSort());
        // 对于随机数组，归并略快于希尔排序。
        BenchmarkUtil.benchmark(1000000,
                new MergeOptimized(),
                new ShellSort());
    }
}