package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dingdong
 * @since 2021/4/29
 */
@Slf4j
@SuppressWarnings("deprecation")
public class IntSortCheck {

    public static void main(String[] args) {
        List<BaseIntSort> algos = List.of(new BinSort(),
                new Bubble(),
                new BubbleEarlyTerminated(),
                new BubbleMinimizedBounds(),
                new HeapSort(),
                new Insertion(),
                new Merge(),
                new MergeBU(),
                new MergeInPlace(),
                new MergeOptimizedDualThread(),
                new MergeOptimizedParallel(),
                new Quick(),
                new QuickOptimized(),
                new QuickNonRecurse(),
                new QuickThreeWaySplit(),
                new Selection(),
                new ShellSort(),
                new ShellParallelSort());
        algos.forEach(IntSortCheck::check);
        List<IntSort> algos1 = algos.stream()
                .filter(algo -> algo instanceof IntSort)
                .map(t -> (IntSort) t)
                .collect(Collectors.toList());
        algos1.forEach(IntSortCheck::checkInterval);
    }

    private static void check(BaseIntSort algo) {
        try {
            BenchmarkUtil.check(algo);
        } catch (Throwable e) {
            log.error("[{}] 校验出错：{}", algo.name(), e.getMessage());
        } finally {
            if (algo instanceof Closeable) {
                try {
                    ((Closeable) algo).close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private static void checkInterval(IntSort algo) {
        try {
            BenchmarkUtil.checkInterval(algo);
        } catch (Throwable e) {
            log.error("[{}] 校验出错：{}", algo.name(), e.getMessage());
        } finally {
            if (algo instanceof Closeable) {
                try {
                    ((Closeable) algo).close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
