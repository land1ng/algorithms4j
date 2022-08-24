package com.burndy.algorithms.sort.utils;

/**
 * 堆的操作 1-based
 *
 * @author dranfree
 * @since 2020.06.06
 */
public abstract class HeapUtil {

    public enum PQType { MIN_PQ, MAX_PQ }

    public static void swim(int[] a, int k, int l, PQType pqType) {
        for (; k > l && priorTo(a, k / 2, k, pqType); k /= 2)
            SortUtil.exch(a, k / 2, k);
    }

    public static void sink(int[] a, int k, int h, PQType pqType) {
        while (2 * k <= h) {
            int i = 2 * k;
            // 选择两个子节点中较大的那一个
            if (i < h && priorTo(a, i, i + 1, pqType))
                i++;
            // 让父节点与子节点中较大的那个交换
            if (priorTo(a, k, i, pqType)) {
                SortUtil.exch(a, k, i);
                k = i;
            } else {
                break;
            }
        }
    }

    private static boolean priorTo(int[] a, int i, int j, PQType pqType) {
        if (pqType == PQType.MAX_PQ)
            return SortUtil.less(a, i, j);
        else
            return !SortUtil.lessEqual(a, i, j);
    }
}