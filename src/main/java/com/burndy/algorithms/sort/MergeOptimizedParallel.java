package com.burndy.algorithms.sort;

import com.burndy.algorithms.sort.utils.BenchmarkUtil;

/**
 * 并行的归并排序：线程的创建和销毁会消耗大量系统资源
 *
 * @author dranfree
 * @since 2020.06.28
 */
@Deprecated
public class MergeOptimizedParallel extends MergeOptimized {

    private static final int PARALLEL_LIMIT = 10000;

    @Override
    protected void sort(int[] a, int[] aux, int lo, int hi) {
        // 递归的终止条件：数组长度为1的时候自然就是有序的
        if (lo >= hi)
            return;
        // 优化点1：小数组使用插入排序
        if (lo >= hi - INSERTION_BOUND) {
            Insertion.sortRange(a, lo, hi);
            return;
        }
        int mi = lo + (hi - lo) / 2;
        Thread t1 = new Thread(() -> sort(a, aux, lo, mi));
        Thread t2 = new Thread(() -> sort(a, aux, mi + 1, hi));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        merge(a, aux, lo, mi, hi);  // 合并子数组
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new MergeOptimizedParallel());
        BenchmarkUtil.benchmark(new MergeOptimizedParallel());
    }
}