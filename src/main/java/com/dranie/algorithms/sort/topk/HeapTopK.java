package com.dranie.algorithms.sort.topk;

import com.dranie.algorithms.sort.utils.HeapUtil;
import com.dranie.algorithms.utils.In;

import java.io.File;

/**
 * 1-based
 *
 * @author dranfree
 * @since 2020.06.06
 */
abstract class HeapTopK implements TopK {

    static class MaxHeapTopK extends HeapTopK {
        MaxHeapTopK() { super(HeapUtil.PQType.MIN_PQ); }
    }
    static class MinHeapTopK extends HeapTopK {
        MinHeapTopK() { super(HeapUtil.PQType.MAX_PQ); }
    }

    private int[] heap; // 小顶堆或大顶堆 1-based
    private int   size; // 堆中当前数据大小

    private final HeapUtil.PQType pqType;

    public HeapTopK(HeapUtil.PQType pqType) { this.pqType = pqType; }

    /**
     * 从数据文件中提取数据并过滤最小的 k 个数据
     *
     * @param source
     * @param k
     * @return 最小的k个整数
     */
    @Override
    public int[] fetch(File source, int k) {
        initHeap(k);
        In in = checkFile(source);
        while (in.hasNextChar())
            insert(in.readInt());
        int[] topk = new int[size];
        System.arraycopy(heap, 1, topk, 0, size);
        return topk;
    }

    private void initHeap(int k) {
        heap = new int[k + 1];
        size = 0;
    }

    private void insert(int data) {
        if (size < heap.length - 1)
            insertAsTail(data);
        else
            insertAsHead(data);
    }

    private int top() { return heap[1]; }

    // 替换堆顶
    private void insertAsHead(int data) {
        if (pqType == HeapUtil.PQType.MIN_PQ) {
            if (data < top()) return;
        } else {
            if (top() < data) return;
        }
        heap[1] = data;
        HeapUtil.sink(heap, 1, size, pqType);
    }
    // 追加堆尾
    private void insertAsTail(int data) {
        heap[++size] = data;
        HeapUtil.swim(heap, size, 1, pqType);
    }

    private In checkFile(File file) {
        In in = new In(file);
        if (!in.exists())
            throw new IllegalArgumentException("文件不存在！");
        else
            return in;
    }
}