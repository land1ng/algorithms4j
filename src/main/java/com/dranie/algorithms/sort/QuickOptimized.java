package com.dranie.algorithms.sort;

/**
 * @author dranfree
 * @since 2020.05.30
 */
public class QuickOptimized extends Quick {

    private static final int INSERTION_BOUND = 16;

    @Override
    public String name() {
        return "单轴快速排序优化版";
    }

    @Override
    protected void sort(int[] a, int lo, int hi) {
        if (hi > lo) {
            // 优化点：小数组使用插入排序
            if (hi <= lo + INSERTION_BOUND) {
                Insertion.sort(a, lo, hi);
                return;
            }
            int pivot = doPartition(a, lo, hi);
            sort(a, lo, pivot - 1);
            sort(a, pivot + 1, hi);
        }
    }
}