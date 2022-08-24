package com.burndy.algorithms.sort;

import java.util.Arrays;

/**
 * 抽象优先队列，实现一些模板方法。
 *
 * @author dingdong
 * @since 2021/4/25
 */
@SuppressWarnings("SameParameterValue")
public abstract class AbstractPQ<T extends Comparable<T>> implements PQ<T> {

    private T[] heap; // 堆，索引 0 的位置留空
    private int size; // 堆的大小

    private static final int INIT_HEAP_SIZE = 16;

    public AbstractPQ() {
        this(INIT_HEAP_SIZE);
    }

    @SuppressWarnings("unchecked")
    public AbstractPQ(int size) {
        heap = (T[]) new Comparable[size];
    }

    @SuppressWarnings("unchecked")
    public AbstractPQ(T[] data) {
        heap = (T[]) new Comparable[size];
        for (T element : data)
            insert(element);
    }

    /**
     * 向队列中插入一个元素
     *
     * @param element
     */
    @Override
    public void insert(T element) {
        checkHeapSize(size + 1);
        heap[++size] = element;
        swim(size); // 将新增的元素上浮到正确的位置
    }

    private void checkHeapSize(int required) {
        if (required >= heap.length) inflate(2 * heap.length);
    }

    @SuppressWarnings("unchecked")
    private void inflate(int newSize) {
        T[] newHeap = (T[]) new Comparable[newSize];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    /**
     * 上浮
     *
     * @param k
     * @see MinPQImpl#swim(int)
     * @see MaxPQImpl#swim(int)
     */
    protected abstract void swim(int k);

    /**
     * 下沉
     *
     * @param k
     * @see MinPQImpl#sink(int)
     * @see MaxPQImpl#sink(int)
     */
    protected abstract void sink(int k);

    /**
     * 删除并返回堆顶的元素
     *
     * @return 堆顶的元素
     */
    protected T delTop() {
        T top = top();          // 1.从根节点得到最大元素
        exch(1, size--);     // 2.将其和最后一个节点交换
        heap[size + 1] = null;  // 3.便于垃圾回收
        sink(1);             // 4.恢复堆的有序性
        return top;
    }

    protected boolean less(int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    protected void exch(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    @Override
    public int size() {
        return size;
    }

    protected T top() {
        return heap[1];
    }

    @Override
    public String toString() {
        return Arrays.toString(heap);
    }
}
