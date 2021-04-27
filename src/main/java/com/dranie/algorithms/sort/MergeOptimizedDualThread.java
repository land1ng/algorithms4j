package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;

import java.util.concurrent.CountDownLatch;

/**
 * 双路并行归并
 *
 * @author dranfree
 * @since 2020.06.28
 */
public class MergeOptimizedDualThread extends MergeOptimized {

    private static final boolean canBeParallel = Runtime.getRuntime().availableProcessors() > 1;

    @Override
    public String name() {
        return "双路归并";
    }

    @Override
    public void sort(int[] a) {
        if (canBeParallel) {
            int[] aux = new int[a.length];
            int mi = a.length / 2;
            final CountDownLatch latch = new CountDownLatch(2);
            new Thread(() -> {
                sort(a, aux, 0, mi);
                latch.countDown();
            }).start();
            new Thread(() -> {
                sort(a, aux, mi + 1, a.length - 1);
                latch.countDown();
            }).start();
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            merge(a, aux, 0, mi, a.length - 1);
        } else {
            super.sort(a);
        }
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new MergeOptimizedDualThread());
        BenchmarkUtil.benchmark(new MergeOptimizedDualThread());
        BenchmarkUtil.benchmark(5000000,
                new MergeOptimized(),
                new MergeOptimizedDualThread());
        BenchmarkUtil.fuckingJDK(new MergeOptimizedDualThread());
    }
}