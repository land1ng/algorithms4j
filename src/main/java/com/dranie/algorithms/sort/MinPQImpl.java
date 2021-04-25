package com.dranie.algorithms.sort;

/**
 * 最小优先队列数组实现：
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class MinPQImpl<T extends Comparable<T>> extends AbstractPQ<T> implements MinPQ<T> {

    public MinPQImpl() {
    }

    public MinPQImpl(int size) {
        super(size);
    }

    public MinPQImpl(T[] data) {
        super(data);
    }

    /**
     * 返回最大的元素
     *
     * @return
     */
    @Override
    public T min() {
        return top();
    }

    /**
     * 删除并返回最大的元素
     *
     * @return
     */
    @Override
    public T delMin() {
        return delTop();
    }

    /**
     * 上浮：对应 insert 操作
     *
     * @param k
     */
    protected void swim(int k) {
        for (; k > 1 && less(k, k / 2); k /= 2)
            exch(k / 2, k);
    }

    /**
     * 下沉：对应 delMax 操作
     *
     * @param k
     */
    protected void sink(int k) {
        while (2 * k <= size()) {
            int i = 2 * k;
            // 选择两个子节点中较大的那一个
            if (i < size() && less(i + 1, i)) i++;
            // 让父节点与子节点中较大的那个交换
            if (less(i, k)) {
                exch(k, i);
                k = i;
            } else {
                break;
            }
        }
    }
}