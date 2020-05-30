package com.dranie.algorithms.sort;

import com.dranie.algorithms.utils.Stopwatch;

/**
 * 一些排序要用到的通用方法
 *
 * @author dranfree
 * @since 2020.05.30
 */
public abstract class SortCommon implements Sort {

    /**
     * 比较两个对象
     *
     * @return
     */
    protected boolean less(int[] a, int i, int j) {
        return a[i] < a[j];
    }

    protected void exch(int[] a, int i, int j) {
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }


    /**
     * 测试排序性能
     *
     * @param n 数量级
     */
    protected void testPerformance(int n) {
        Stopwatch watch = new Stopwatch();
        // 1.完全随机数组
        int[] a1 = createRandom(n);
        watch.start("[" + this.getClass().getSimpleName() + "] " + "随机");
        sort(a1);
        watch.finishReset();
        // 2.正向有序数组
        int[] a2 = createSorted(n);
        watch.start("[" + this.getClass().getSimpleName() + "] " + "有序");
        sort(a2);
        watch.finishReset();
        // 3.反向有序数组
        int[] a3 = createInvert(n);
        watch.start("[" + this.getClass().getSimpleName() + "] " + "反序");
        sort(a3);
        watch.finishReset();
    }

    public static void testPerformance(int n, SortCommon... sorts) {
        for (SortCommon sort : sorts)
            sort.testPerformance(n);
    }

    public static int[] createRandom(int n) {
        int[] a = createSorted(n);
        Shuffle.shuffle(a);
        return a;
    }
    public static int[] createSorted(int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++)
            a[i] = i + 1;
        return a;
    }
    public static int[] createInvert(int n) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++)
            a[i] = n - i;
        return a;
    }
}