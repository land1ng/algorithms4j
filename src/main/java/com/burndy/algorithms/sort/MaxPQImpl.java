package com.burndy.algorithms.sort;

/**
 * 最大优先队列数组实现：
 *
 * @author dranfree
 * @since 2020.05.30
 */
public class MaxPQImpl<T extends Comparable<T>> extends AbstractPQ<T> implements MaxPQ<T> {

    public MaxPQImpl() {
    }

    public MaxPQImpl(int size) {
        super(size);
    }

    public MaxPQImpl(T[] data) {
        super(data);
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
        while (2 * k <= size()) {
            int i = 2 * k;
            // 选择两个子节点中较大的那一个
            if (i < size() && less(i, i + 1)) i++;
            // 让父节点与子节点中较大的那个交换
            if (less(k, i)) {
                exch(k, i);
                k = i;
            } else {
                break;
            }
        }
    }

    /**
     * 返回最大的元素
     *
     * @return
     */
    @Override
    public T max() {
        return top();
    }

    /**
     * 删除并返回最大的元素
     *
     * @return
     */
    @Override
    public T delMax() {
        return delTop();
    }
}