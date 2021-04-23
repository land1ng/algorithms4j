package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.BenchmarkUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 非递归快速排序（性能比递归实现快一些）：
 *
 * 使用一个循环来将弹出栈的子数组切分并将结果子数组重新压入栈
 *
 * 注意：先将较大的子数组压入栈，这样就可以保证最多只会有 lgN 个元素。
 *
 *
 * 递归本质就是方法压栈，因此将其改造成非递归也就是是用一个栈来保存中间变量。
 *
 * @author dranfree
 * @since 2020.05.31
 */
public class QuickNonRecurse extends Quick {

    private static final int INSERTION_BOUND = 16;

    @Override
    public String name() {
        return "单轴快速排序非递归版本";
    }

    private static class Interval {

        final int lo;
        final int hi;

        Interval(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }
    }

    @Override
    protected void sort(int[] a, int lo, int hi) {
        Deque<Interval> stack = new LinkedList<>();
        stack.push(new Interval(lo, hi));
        Interval interval;
        while (!stack.isEmpty() && (interval = stack.pop()) != null) {
            int l = interval.lo;
            int h = interval.hi;
            if (h <= l + INSERTION_BOUND) {
                Insertion.sort(a, l, h);
                continue;
            }
            int m = doPartition(a, l, h);
            if (m - 1 > l) stack.push(new Interval(l, m - 1));
            if (m + 1 < h) stack.push(new Interval(m + 1, h));
        }
    }

    public static void main(String[] args) {
        BenchmarkUtil.check(new QuickNonRecurse());
        BenchmarkUtil.benchmark(5000000,
                new QuickOptimized(),
                new QuickNonRecurse());
    }
}