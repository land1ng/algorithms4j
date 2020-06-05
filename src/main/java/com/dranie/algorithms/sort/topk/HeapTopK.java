package com.dranie.algorithms.sort.topk;

import java.io.File;

/**
 * 使用小顶堆或大顶堆
 *
 * 小顶堆，堆顶是最小的元素，用于最大TopK问题。
 * 大顶堆，堆顶是最大的元素，用于最小TopK问题。
 *
 * @author dran
 * @since 2020-06-04
 */
public class HeapTopK implements TopK {

    private int[] initHeap(int size) { return new int[size]; }

    /**
     * 从数据文件中提取数据并过滤最小的 k 个数据
     *
     * @param source
     * @param k
     * @return 最小的k个整数
     */
    @Override
    public int[] min(File source, int k) {
        int[] heap = initHeap(k);
        return new int[0];
    }

    /**
     * 从数据文件中提取数据并过滤最大的 k 个数据
     *
     * @param source
     * @param k
     * @return 最大的k个整数
     */
    @Override
    public int[] max(File source, int k) {
        int[] heap = initHeap(k);
        return new int[0];
    }
}
