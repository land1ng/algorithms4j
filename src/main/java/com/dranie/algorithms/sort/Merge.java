package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;
import com.dranie.algorithms.utils.Optimizable;

/**
 * 自顶向下的归并排序
 *
 * @auxthor dranfree
 * @since 2020.05.30
 */
@Optimizable
public class Merge implements CompareBasedSort {

    @Override
    public void sort(int[] a) {
        sort(a, new int[a.length], 0, a.length - 1);
    }

    @Optimizable
    protected void sort(int[] a, int[] aux, int lo, int hi) {
        // 递归的终止条件：数组长度为1的时候自然就是有序的
        if (lo >= hi)
            return;
        int mi = lo + (hi - lo) / 2;
        sort(a, aux, lo, mi);       // 左半边排序
        sort(a, aux, mi + 1, hi);   // 右半边排序
        merge(a, aux, lo, mi, hi);  // 合并子数组
    }

    /**
     * 借助辅助数组归并两个子数组，前提是两个子数组是有序的。
     *
     * @param a     原数组
     * @param aux   辅助数组
     * @param lo    左子数组下界
     * @param mi    左子数组上界
     * @param hi    右子数组上界
     */
    protected void merge(int[] a, int[] aux, int lo, int mi, int hi) {
        // 1.首先将 [lo, hi] 拷贝到辅助数组中相应的位置
        System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
        // 2.合并两个子数组
        // 维护两个子数组的两个指针
        int i = lo, j = mi + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mi)          a[k] = aux[j++];
            else if (j > hi)          a[k] = aux[i++];
            else if (less(aux, i, j)) a[k] = aux[i++];
            else                      a[k] = aux[j++];
        }
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new Merge());
        BenchmarkUtil.benchmark(new Merge());
        BenchmarkUtil.benchmark(500000,
                new Merge(),
                new MergeBU(),
                new MergeOptimized(),
                new Quick(),
                new QuickOptimized(),
                new HeapSort());
    }
}