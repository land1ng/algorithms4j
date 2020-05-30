package com.dranie.algorithms.sort;

/**
 * 自底向上的归并排序
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class MergeBU extends Merge {

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    public void sort(int[] a) {
        int[] aux = new int[a.length];
        // 关键点：我们直接从最底部长度为1的子数组开始归并，避免了递归的消耗。
        for (int sz = 1; sz < a.length; sz += sz)                 // sz：子数组大小，每次循环之后都会翻倍。
            for (int lo = 0; lo < a.length - sz; lo += sz + sz) { // lo：子数组索引
                // 和自顶向下归并相同的优化：通过比较左子数组上界和右子数组下界的元素大小来决定是否需要执行归并
                if (less(a, lo + sz -1, lo + sz))
                    continue;
                merge(a, aux, lo, lo + sz -1, Math.min(lo + sz + sz - 1, a.length - 1));
            }
    }
}