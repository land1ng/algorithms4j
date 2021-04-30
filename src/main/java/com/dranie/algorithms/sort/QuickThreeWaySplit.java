package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.base.IntSort;
import com.dranie.algorithms.sort.utils.BenchmarkUtil;

/**
 * 三向切分的快速排序
 *
 * 将数组切分为三部分，分别对应于小于、等于、大于切分元素的数组元素。
 *
 * 这种优化后的快排主要是用于排序含有大量重复元素的数组
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class QuickThreeWaySplit implements CompareBasedSort, IntSort {

    private static final int INSERTION_BOUND = 16;

    @Override
    public String name() {
        return "单轴快排-三向切分";
    }

    @Override
    public void sort(int[] a, int lo, int hi) {
//        if (hi <= lo)
//            return;
        if (hi <= lo + INSERTION_BOUND) {
            Insertion.sortRange(a, lo, hi);
            return;
        }
        int lt = lo, i = lo + 1, gt = hi;
        int v = a[lo];
        // a[lo] 将数组分为三部分：比 v 小、等于 v、比 v 大
        // 指针 i 是从左往右扫描的
        while (i <= gt) {
            int cmp = a[i] - v;
            if      (cmp < 0) exch(a, lt++, i++); // 交换 a[lt] 和 a[i]
            else if (cmp > 0) exch(a, gt--, i  ); // 交换 a[gt] 和 a[i]
            else              i++;
        } // 现在 a[lo..lt-1] < v = a[lt..gt] <a[gt+1..hi] 成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new QuickThreeWaySplit());
        BenchmarkUtil.benchmark(5000000,
                new QuickOptimized(),
                new QuickThreeWaySplit());
    }
}