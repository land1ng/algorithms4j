package com.burndy.algorithms.sort;

/**
 * @author dranfree
 * @since 2020.05.30
 */
public class QuickOptimized extends Quick {

    private static final int INSERTION_BOUND = 16;

    @Override
    public String name() {
        return "单轴快排-优化版";
    }

    @Override
    protected void innerSort(int[] a, int lo, int hi) {
        if (hi > lo) {
            // 优化点：小数组使用插入排序
            if (hi <= lo + INSERTION_BOUND) {
                Insertion.sortRange(a, lo, hi);
                return;
            }
            int pivot = doPartition(a, lo, hi);
            innerSort(a, lo, pivot - 1);
            innerSort(a, pivot + 1, hi);
        }
    }
}