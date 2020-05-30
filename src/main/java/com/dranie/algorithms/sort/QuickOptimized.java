package com.dranie.algorithms.sort;

/**
 * @author dranfree
 * @since 2020.05.30
 */
public class QuickOptimized extends Quick {

    private static final int INSERTION_BOUND = 16;

    private static final Insertion INSERTION = new Insertion();

    @Override
    protected void sort(int[] a, int lo, int hi) {
        if (hi > lo) {
            // 优化点：小数组使用插入排序
            if (hi <= lo + INSERTION_BOUND) {
                INSERTION.sort(a, lo, hi);
                return;
            }
            int pivot = doPartition(a, lo, hi);
            sort(a, lo, pivot - 1);
            sort(a, pivot + 1, hi);
        }
    }
}