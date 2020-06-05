package com.dranie.algorithms.sort;

/**
 * 原地归并：不需要辅助数组
 *
 * 时间复杂度：O(n(logn)^2)
 * 空间复杂度：O(1)
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class MergeInPlace extends SortAdapter {

    private static final int INSERTION_BOUND = 15;

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        sort(a, 0, a.length - 1);
    }

    private void sort(int[] a, int lo, int hi) {
        // 递归的终止条件：数组长度为1的时候自然就是有序的
//        if (lo >= hi)
//            return;
        // 优化点1：小数组使用插入排序
        if (lo >= hi - INSERTION_BOUND) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mi = lo + (hi - lo) / 2;
        sort(a, lo, mi);       // 左半边排序
        sort(a, mi + 1, hi);   // 右半边排序
        // 优化点2：比较左子数组上界和右子数组下界，提前结束排序。
        if (less(a, mi, mi + 1))
            return;
        merge_inplace(a, lo, mi, hi);  // 合并子数组
    }

    /**
     * 重点：原地归并
     *
     * @param a     原数组
     * @param lo    左子数组下界
     * @param mi    左子数组上界
     * @param hi    右子数组上界
     */
    private void merge_inplace(int[] a, int lo, int mi, int hi) {
        int i = lo, j = mi + 1;
        while (i < j && j <= hi) {
            // 1.确定左边界
            while (i < j && lessEqual(a, i, j))
                i++;
            // 2.确定右边界
            int index = j;
            while (j <= hi && less(a, j, i))
                j++;
            // 3.内存反转
            reverseBlocks(a, i, index - 1, j - 1);
            i += (j - index);
        }
    }

    /**
     * 内存反转，即交换相邻的两块内存，在《编程珠玑》中又被称为手摇算法。
     *
     * 给定序列：[a1,a2,...,am,b1,b2,...,bn]，将其变为：[b1,b2,...,bn,a1,a2,...,am]，要求空间 O(1)。
     *
     * 编程思路：
     * 1.先反转 [a1,a2,...,am] 得到 [am,...,a2,a1]
     * 2.再反转 [b1,b2,...,bn] 得到 [bn,...,b2,b1]
     * 3.最后反转整体 [am,...,a2,a1,bn,...,b2,b1] 得到 [b1,b2,...,bn,a1,a2,...,am]
     *
     * 反转 [lo,...,mi,...hi] 序列
     *
     * @param a
     * @param lo
     * @param mi
     * @param hi
     */
    private void reverseBlocks(int[] a, int lo, int mi, int hi) {
        reverse(a, lo, mi);     // [lo,...,mi] => [mi,...,lo]
        reverse(a, mi + 1, hi); // [mi+1,...hi] => [hi,...,mi+1]
        reverse(a, lo, hi);     // [mi,...,lo,hi,...,mi+1] => [mi+1,...,hi,lo,...mi]
    }
    private void reverse(int[] a, int x, int y) {
        while (x < y)
            exch(a, x++, y--);
    }
}