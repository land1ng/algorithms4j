package com.dranie.algorithms.sort;

import java.util.Arrays;

/**
 * 最大优先队列数组实现：
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class MaxPQImpl<T extends Comparable<T>> implements MaxPQ<T> {

    private T[] heap; // 堆，索引 0 的位置留空
    private int size; // 堆的大小

    private static final int INIT_HEAP_SIZE = 16;

    public MaxPQImpl() { this(INIT_HEAP_SIZE); }
    @SuppressWarnings("unchecked")
    public MaxPQImpl(int size) { heap = (T[]) new Comparable[size]; }
    @SuppressWarnings("unchecked")
    public MaxPQImpl(T[] data) {
        heap = (T[]) new Comparable[size];
        for (T element : data)
            insert(element);
    }


    /**
     * 上浮：对应 insert 操作
     *
     * @param k
     */
    protected void swim(int k) {
        for (; k > 1 && less(k / 2, k); k /= 2)
            exch(k / 2, k);
    }

    /**
     * 下沉：对应 delMax 操作
     *
     * @param k
     */
    protected void sink(int k) {
        while (2 * k <= size) {
            int i = 2 * k;
            // 选择两个子节点中较大的那一个
            if (i < size && less(i, i + 1))
                i++;
            // 让父节点与子节点中较大的那个交换
            if (less(k, i)) {
                exch(k, i);
                k = i;
            } else {
                break;
            }
        }
    }

    private boolean less(int i, int j) { return heap[i].compareTo(heap[j]) < 0; }
    private void    exch(int i, int j) {
        T  temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * 向队列中插入一个元素
     *
     * @param element
     */
    public void insert(T element) {
        checkHeapSize(size + 1);
        heap[++size] = element;
        swim(size); // 将新增的元素上浮到正确的位置
    }

    private void checkHeapSize(int required) {
        if (required >= heap.length)
            inflate(2 * heap.length);
    }
    @SuppressWarnings("unchecked")
    private void inflate(int newSize) {
        T[] newHeap = (T[]) new Comparable[newSize];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }

    /**
     * 返回最大的元素
     *
     * @return
     */
    public T max() { return heap[1]; }

    /**
     * 删除并返回最大的元素
     *
     * @return
     */
    public T delMax() {
        T max = heap[1];        // 1.从根节点得到最大元素
        exch(1, size--);        // 2.将其和最后一个节点交换
        heap[size + 1] = null;  // 3.便于垃圾回收
        sink(1);                // 4.恢复堆的有序性
        return max;
    }

    public boolean isEmpty() { return size == 0; }

    public int        size() { return size;      }


    @Override
    public String toString() {
        return Arrays.toString(heap);
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQImpl<>();
        for (int i = 0; i < 50; i++)
            maxPQ.insert(i + 1);
        System.out.println(maxPQ);
        maxPQ.delMax();
        System.out.println(maxPQ);
        maxPQ.delMax();
        System.out.println(maxPQ);
        maxPQ.delMax();
        System.out.println(maxPQ);
        maxPQ.delMax();
        System.out.println(maxPQ);
    }
}