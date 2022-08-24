package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.utils.BenchmarkUtil;

/**
 * 归并排序的几个优化
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class MergeOptimized extends Merge {

    protected static final int INSERTION_BOUND = 15;

    @Override
    public String name() {
        return "归并排序-优化版";
    }

    @Override
    protected void sort(int[] a, int[] aux, int lo, int hi) {
        // 递归的终止条件：数组长度为1的时候自然就是有序的
//        if (lo >= hi)
//            return;
        // 优化点1：小数组使用插入排序
        if (lo >= hi - INSERTION_BOUND) {
            Insertion.sortRange(a, lo, hi);
            return;
        }
        int mi = lo + (hi - lo) / 2;
        sort(a, aux, lo, mi);       // 左半边排序
        sort(a, aux, mi + 1, hi);   // 右半边排序
        // 优化点2：比较左子数组上界和右子数组下界，提前结束排序。
        if (less(a, mi, mi + 1)) return;
        merge(a, aux, lo, mi, hi);  // 合并子数组
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new MergeOptimized());
        BenchmarkUtil.fuckingJDK(new MergeOptimized());
    }
}