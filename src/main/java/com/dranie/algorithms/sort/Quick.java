package com.dranie.algorithms.sort;

import java.util.Arrays;

/**
 * 单轴快排
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class Quick extends SortCommon {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    public void sort(int[] a) {
        Shuffle.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        if (hi > lo) {
            int pivot = doPartition(a, lo, hi);
            sort(a, lo, pivot - 1);
            sort(a, pivot + 1, hi);
        }
    }

    private int doPartition(int[] a, int lo, int hi) {
        int i = lo; // 暂定 lo 为切分点
        int j = hi + 1;
        while (true) {
            // i 在 hi 停住或在 a[i] >= a[lo] 的地方停住
            // j 在 lo 停住或在 a[j] <= a[lo] 的地方停住
            while (less(a, ++i, lo)) if (i == hi) break;
            while (less(a, lo, --j)) if (j == lo) break;
            if (i >= j)
                break;
            exch(a, i, j);
        }
        // 最后 break 的时候，i >= j 的，而 a[j] <= a[lo]，因此将 a[lo] 与 a[j] 交换可以保证 j 左边都小于 a[j]。
        exch(a, lo, j);
        return j;
    }

    public static void main(String[] args) {
        int[] a = createRandom(10);
        System.out.println("b: " + Arrays.toString(a));
        new Quick().sort(a);
        System.out.println("a: " + Arrays.toString(a));
        SortCommon.testPerformance(5000000,
                new MergeOptimized(),
                new HeapSort(),
                new Quick());
    }
}