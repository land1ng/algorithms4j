package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.base.IntSort;

/**
 * 单轴快排
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class Quick implements CompareBasedSort, IntSort {

    @Override
    public String name() {
        return "单轴快排";
    }

    @Override
    public void sort(int[] a, int lo, int hi) {
        Shuffle.shuffle(a, lo, hi);
        innerSort(a, lo, hi);
    }

    protected void innerSort(int[] a, int lo, int hi) {
        if (hi > lo) {
            int pivot = doPartition(a, lo, hi);
            innerSort(a, lo, pivot - 1);
            innerSort(a, pivot + 1, hi);
        }
    }

    protected int doPartition(int[] a, int lo, int hi) {
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
}