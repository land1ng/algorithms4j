package com.burndy.algorithms.sort.utils;

import com.burndy.algorithms.sort.HeapSort;
import com.burndy.algorithms.sort.Merge;
import com.burndy.algorithms.sort.Quick;
import com.burndy.algorithms.sort.Shuffle;
import com.burndy.algorithms.utils.Tuple2;
import com.dranie.algorithms.sort.*;
import com.burndy.algorithms.sort.base.BaseIntSort;
import com.burndy.algorithms.sort.base.IntSort;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 性能测试工具类
 *
 * @author dranfree
 * @since 2020.05.30
 */
@Slf4j
public abstract class BenchmarkUtil {


    private static String name(final BaseIntSort algo) {
        return algo.name();
    }

    /**
     * 多个排序算法对比测试
     *
     * @param algos
     */
    public static void comparing(BaseIntSort... algos) {
        // 正确性校验
        List<BaseIntSort> candidates = Arrays.stream(algos).map(algo -> Tuple2.of(algo, checkSilent(algo))).filter(tuple -> {
            if (!tuple._2()) {
                log.warn("算法 [{}] 未通过正确性测试，跳过对比测试！", tuple._1().name());
            }
            return tuple._2();
        }).map(Tuple2::_1).collect(Collectors.toList());
        // 时间复杂度比较测试
        IntStream.of(100, 1000, 10000, 100000, 1000000, 5000000).forEach(scale -> {
            log.info("[比较测试] 数据量：{}", scale);
            compareTime(candidates, createRandom(scale));
            compareTime(candidates, createEquals(scale));
            compareTime(candidates, createInvert(scale));
            compareTime(candidates, createSorted(scale));
        });
    }

    private static void compareTime(List<BaseIntSort> candidates, Sample sample) {
        List<Tuple2<BaseIntSort, Long>> result4random = candidates.stream().map(algo -> {
            long time4random = elapsedTime(algo, sample.copy().data);
            return Tuple2.of(algo, time4random);
        }).sorted(Comparator.comparing(Tuple2::_2)).collect(Collectors.toList());
        StringBuilder result = new StringBuilder();
        AtomicInteger index = new AtomicInteger();
        result4random.forEach(single -> {
            result.append(single._1().name()).append("(").append(single._2()).append("ms)");
            if (index.incrementAndGet() != candidates.size()) {
                result.append(" -> ");
            }
        });
        log.info(" ===> [{}] {}", sample.name, result.toString());
    }

    /**
     * 单个算法性能测试
     *
     * @param algo
     */
    public static void benchmark(final BaseIntSort algo) {
        if (!checkSilent(algo)) return;
        Arrays.asList(100, 1000, 10000, 100000, 1000000, 5000000).forEach(scale -> benchmark0(algo, scale));
    }

    /**
     * 性能测试
     *
     * @param scale 数据量
     * @param algos 算法列表
     */
    public static void benchmark(final int scale, final BaseIntSort... algos) {
        Arrays.asList(algos).forEach(algo -> {
            if (!checkSilent(algo))
                return;
            benchmark0(algo, scale);
        });
    }

    private static void benchmark0(final BaseIntSort algo, final int scale) {
        log.info("[{}] 性能测试，数据量：{}", name(algo), scale);
        benchmark1(algo, createRandom(scale));
        benchmark1(algo, createSorted(scale));
        benchmark1(algo, createInvert(scale));
        benchmark1(algo, createEquals(scale));
    }
    private static void benchmark1(final BaseIntSort algo, final Sample sample) {
        log.info("[{}] 性能测试，{}...消耗时间：{}", name(algo), sample.name, elapsedTime(algo, sample.data));
    }

    /**
     * 使用给定数组做测试
     *
     * @param a
     * @param algos
     */
    public static void benchmark(final int[] a, final BaseIntSort... algos) {
        Arrays.asList(algos).forEach(algo -> {
            if (!checkSilent(algo)) return;
            int[] copy = new int[a.length];
            System.arraycopy(a, 0, copy, 0, a.length);
            log.info("[{}] 给定数组测试...消耗时间：{}", name(algo), elapsedTime(algo, copy));
        });
    }

    /**
     * 和 JDK 自带排序进行比较
     *
     * @param algo
     */
    public static void fuckingJDK(final BaseIntSort algo) {
        if (!checkSilent(algo)) return;
        Arrays.asList(100, 1000, 10000, 100000, 1000000, 5000000).forEach(scale -> {
            log.info("[{}] Fucking JDK! 数据量: {}", name(algo), scale);
            int score = 0;
            score += fuckingJDK0(algo, createRandom(scale));
            score += fuckingJDK0(algo, createSorted(scale));
            score += fuckingJDK0(algo, createInvert(scale));
            score += fuckingJDK0(algo, createEquals(scale));
            String ret;
            if (score == 4 - score) {
                ret = "打成平局！";
            } else {
                if (score > 4 - score)
                    ret = "你赢了！";
                else
                    ret = "你输了！";
            }
            log.info("[{}] Fucking JDK! {}:{}, {}", name(algo), score, 4 - score, ret);
        });
    }
    private static int fuckingJDK0(final BaseIntSort algo, final Sample sample) {
        int[] data = sample.data;
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        long time4You = elapsedTime(algo, data);
        long time4Jdk = elapsedTime(Arrays::sort, copy);
        log.info("[{}] {}...You cost {}ms, Jdk cost {}ms.", name(algo), sample.name, time4You, time4Jdk);
        return time4Jdk > time4You ? 1 : 0;
    }


    private static long elapsedTime(final BaseIntSort algo, final int[] a) {
        long init = System.nanoTime();
        algo.sort(a);
        long done = System.nanoTime();
        return (done - init) / 1000000;
    }


    /**
     * 校验算法正确性
     *
     * @param algo
     */
    public static boolean check(final BaseIntSort algo) { return check(algo, 20); }
    public static boolean check(final BaseIntSort algo, final int n) {
        boolean c1 = check0(algo, createRandom(n));
        boolean c2 = check0(algo, createSorted(n));
        boolean c3 = check0(algo, createInvert(n));
        boolean c4 = check0(algo, createEquals(n));
        if (c1 && c2 && c3 && c4) {
            log.info("[正确性测试] [{}] 算法正确！", name(algo));
            return true;
        } else {
            logCheckFailed(algo);
        }
        return false;
    }
    private static boolean check0(final BaseIntSort algo, final Sample sample) {
        log.debug("[{}] 正确性测试，{}数组...排序之前：{}", name(algo), sample.name, sample.data);
        algo.sort(sample.data);
        if (SortUtil.isSorted(sample.data)) {
            log.debug("[{}] 正确性测试，{}数组...排序成功：{}", name(algo), sample.name, sample.data);
            return true;
        } else {
            log.error("[{}] 正确性测试，{}数组...排序错误：{}", name(algo), sample.name, sample.data);
            return false;
        }
    }
    private static boolean checkSilent(final BaseIntSort algo) {
        int n = 20;
        boolean c1 = checkSilent0(algo, createRandom(n));
        boolean c2 = checkSilent0(algo, createSorted(n));
        boolean c3 = checkSilent0(algo, createInvert(n));
        boolean c4 = checkSilent0(algo, createEquals(n));
        if (c1 && c2 && c3 && c4) {
            return true;
        } else {
            logCheckFailed(algo);
        }
        return false;
    }

    private static boolean checkSilent0(final BaseIntSort algo, final Sample sample) {
        algo.sort(sample.data);
        return SortUtil.isSorted(sample.data);
    }

    private static void logCheckFailed(final BaseIntSort algo) {
        log.error("[正确性测试] [{}] 算法错误...", name(algo));
    }

    public static void checkInterval(IntSort algo) {
        boolean c1 = checkInterval0(algo, createRandom(20));
        boolean c2 = checkInterval0(algo, createSorted(20));
        boolean c3 = checkInterval0(algo, createInvert(20));
        boolean c4 = checkInterval0(algo, createEquals(20));
        if (c1 && c2 && c3 && c4) {
            log.info("[区间测试] [{}] 算法正确！", name(algo));
        } else {
            log.error("[区间测试] [{}] 算法错误...", name(algo));
        }
    }

    private static boolean checkInterval0(final IntSort algo, final Sample sample) {
        log.debug("[{}] 区间测试，{}数组...排序之前：{}", name(algo), sample.name, sample.data);
        algo.sort(sample.data, 0, sample.data.length - 1);
        if (SortUtil.isSorted(sample.data, 0, sample.data.length - 1)) {
            log.debug("[{}] 区间测试，{}数组...排序成功：{}", name(algo), sample.name, sample.data);
            return true;
        } else {
            log.error("[{}] 区间测试，{}数组...排序错误：{}", name(algo), sample.name, sample.data);
            return false;
        }
    }

    public static Sample createRandom(final int n) {
        Sample bd = createSorted(n);
        Shuffle.shuffle(bd.data);
        return Sample.builder().data(bd.data).name("乱序").build();
    }

    public static Sample createSorted(final int n) {
        int[] a = new int[n];
        Arrays.setAll(a, i -> i + 1);
        return Sample.builder().data(a).name("有序").build();
    }
    public static Sample createInvert(final int n) {
        int[] a = new int[n];
        Arrays.setAll(a, i -> n - i);
        return Sample.builder().data(a).name("逆序").build();
    }

    public static Sample createEquals(final int n) {
        int[] a = new int[n];
        Random rd = new Random();
        Arrays.setAll(a, i -> rd.nextInt(n));
        return Sample.builder().data(a).name("等值").build();
    }

    public static void main(String[] args) {
        comparing(new Quick(), new Merge(), new HeapSort(), new IntSort() {
            @Override
            public String name() {
                return "JDK排序";
            }

            /**
             * 排序 [lo, hi] 范围内的数据
             *
             * @param a  待排序数组
             * @param lo 左边界
             * @param hi 右边界
             */
            @Override
            public void sort(int[] a, int lo, int hi) {
                Arrays.sort(a, lo, hi);
            }
        });
    }
}