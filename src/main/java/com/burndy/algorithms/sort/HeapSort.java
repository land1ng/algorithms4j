package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.base.BaseIntSort;

/**
 * 堆排序
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class HeapSort implements CompareBasedSort, BaseIntSort {

    @Override
    public String name() {
        return "堆排序";
    }

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        // 1.构造堆
        int hi = a.length - 1;
        for (int k = hi / 2; k >= 0; k--)
            sink(a, k, hi);
        // 2.循环交换堆顶（最大的那个元素）和堆末尾元素，然后修复堆。
        while (hi > 0) {
            exch(a, 0, hi--); // 缩小堆
            sink(a, 0, hi);   // 修复堆
        }
    }

    /**
     * 下沉
     *
     * @param k0
     * @param h0
     */
    private void sink(int[] a, int k0, int h0) {
        int k = k0 + 1;
        int h = h0 + 1;
        while (2 * k <= h) {
            int i = 2 * k;
            // 选择两个子节点中较大的那一个
            if (i < h && less(a, i - 1, i)) i++;
            // 让父节点与子节点中较大的那个交换
            if (less(a, k - 1, i - 1)) exch(a, k - 1, i - 1);
            else break;
            k = i;
        }
    }

    public static void main(String[] args) {
        new HeapSort().benchmark();
    }
}