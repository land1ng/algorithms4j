package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 并行的希尔排序
 *
 * @deprecated 性能很差
 *
 * @author dranfree
 * @since 2020.08.23
 */
@Slf4j
@Deprecated
public class ShellParallelSort extends ShellSort implements Closeable {

    private final ThreadPoolExecutor tp = new ThreadPoolExecutor(
            10, 10, 5, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>()
    );

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        int len = a.length;
        int h = 1;
        while (h < len / 3) h = 3 * h + 1;
        while (h > 0) {
            CountDownLatch l = null;
            if (h >= 4)
                l = new CountDownLatch(len - h);
            for (int i = h; i < len; i++) {
                if (h >= 4) {
                    tp.execute(new ShellSortTask(i, h, a, l));
                } else {
                    // 退化为传统的插入排序
                    super.sort0(a, i, h);
                }
            }
            if (l != null) {
                try {
                    l.await();
                } catch (InterruptedException e) {
                    // ignore
                }
            }
            h /= 3;
        }
    }

    @RequiredArgsConstructor
    private class ShellSortTask implements Runnable {

        final int i;
        final int h;

        final int[] a;

        final CountDownLatch l;

        @Override
        public void run() {
            for (int i = h; i < a.length; i++) {
                // 对于每个间隔为 h 的子数组使用插入排序
                for (int j = i; j >= h && less(a, j, j - h); j -= h)
                    exch(a, j, j - h);
            }
            l.countDown();
            log.info("i = {} and h = {}, finished.", i, h);
        }
    }

    @Override
    public void close() { tp.shutdown(); }

    public static void main(String[] args) {
//        new ShellParallelSort().benchmark();
        BenchmarkUtil.check(new ShellParallelSort());
        BenchmarkUtil.benchmark(100000,
                new ShellSort(),
                new ShellParallelSort());
    }
}