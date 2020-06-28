package com.dranie.algorithms.sort.utils;

import com.dranie.algorithms.sort.Shuffle;
import com.dranie.algorithms.sort.Sort;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Random;

/**
 * 性能测试工具类
 *
 * @author dranfree
 * @since 2020.05.30
 */
@Slf4j
public abstract class BenchmarkUtil {


    private static String name(final Sort algo) { return algo.getClass().getSimpleName(); }

    /**
     * 单个算法性能测试
     *
     * @param algo
     */
    public static void benchmark(final Sort algo) {
        if (!checkSilent(algo))
            return;
        Arrays.asList(100, 1000, 10000, 100000, 1000000, 5000000)
                .forEach(scale -> benchmark0(algo, scale));
    }

    /**
     * 性能测试
     *
     * @param scale 数据量
     * @param algos 算法列表
     */
    public static void benchmark(final int scale, final Sort... algos) {
        Arrays.asList(algos).forEach(algo -> {
            if (!checkSilent(algo))
                return;
            benchmark0(algo, scale);
        });
    }

    private static void benchmark0(final Sort algo, final int scale) {
        log.info("[{}] 性能测试，数据量：{}", name(algo), scale);
        benchmark1(algo, createRandom(scale));
        benchmark1(algo, createSorted(scale));
        benchmark1(algo, createInvert(scale));
        benchmark1(algo, createEquals(scale));
    }
    private static void benchmark1(final Sort algo, final Sample sample) {
        log.info("[{}] 性能测试，{}...消耗时间：{}", name(algo), sample.name, elapsedTime(algo, sample.data));
    }

    /**
     * 使用给定数组做测试
     *
     * @param a
     * @param algos
     */
    public static void benchmark(final int[] a, final Sort... algos) {
        Arrays.asList(algos).forEach(algo -> {
            if (!checkSilent(algo))
                return;
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
    public static void fuckingJDK(final Sort algo) {
        if (!checkSilent(algo))
            return;
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
    private static int fuckingJDK0(final Sort algo, final Sample sample) {
        int[] data = sample.data;
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        long time4You = elapsedTime(algo, data);
        long time4Jdk = elapsedTime(Arrays::sort, copy);
        log.info("[{}] {}...You cost {}ms, Jdk cost {}ms.", name(algo), sample.name, time4You, time4Jdk);
        return time4Jdk > time4You ? 1 : 0;
    }


    private static long elapsedTime(final Sort algo, final int[] a) {
        long init = System.currentTimeMillis();
        algo.sort(a);
        long done = System.currentTimeMillis();
        return done - init;
    }


    /**
     * 校验算法正确性
     *
     * @param algo
     */
    public static boolean check(final Sort algo) { return check(algo, 20); }
    public static boolean check(final Sort algo, final int n) {
        boolean c1 = check0(algo, createRandom(n));
        boolean c2 = check0(algo, createSorted(n));
        boolean c3 = check0(algo, createInvert(n));
        boolean c4 = check0(algo, createEquals(n));
        if (c1 && c2 && c3 && c4)
            return true;
        else
            logCheckFailed(algo);
        return false;
    }
    private static boolean check0(final Sort algo, final Sample sample) {
        log.info("[{}] 正确性测试，{}数组...排序之前：{}", name(algo), sample.name, sample.data);
        algo.sort(sample.data);
        if (SortUtil.isSorted(sample.data)) {
            log.info("[{}] 正确性测试，{}数组...排序成功：{}", name(algo), sample.name, sample.data);
            return true;
        } else {
            log.error("[{}] 正确性测试，{}数组...排序错误：{}", name(algo), sample.name, sample.data);
            return false;
        }
    }
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private static boolean checkSilent(final Sort algo) {
        int n = 20;
        boolean c1 = checkSilent0(algo, createRandom(n));
        boolean c2 = checkSilent0(algo, createSorted(n));
        boolean c3 = checkSilent0(algo, createInvert(n));
        boolean c4 = checkSilent0(algo, createEquals(n));
        if (c1 && c2 && c3 && c4)
            return true;
        else
            logCheckFailed(algo);
        return false;
    }
    private static boolean checkSilent0(final Sort algo, final Sample sample) {
        algo.sort(sample.data);
        return SortUtil.isSorted(sample.data);
    }
    private static void logCheckFailed(final Sort algo) {
        log.error("[{}] 算法错误...", name(algo));
    }


    private static Sample createRandom(final int n) {
        Sample bd = createSorted(n);
        Shuffle.shuffle(bd.data);
        return Sample.builder().data(bd.data).name("乱序").build();
    }
    private static Sample createSorted(final int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++)
            a[i] = i + 1;
        return Sample.builder().data(a).name("有序").build();
    }
    private static Sample createInvert(final int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++)
            a[i] = n - i;
        return Sample.builder().data(a).name("逆序").build();
    }
    private static Sample createEquals(final int n) {
        int[] a = new int[n];
        Arrays.fill(a, new Random().nextInt(n));
        return Sample.builder().data(a).name("等值").build();
    }
}