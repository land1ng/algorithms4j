package com.dranie.algorithms.sort;

import com.dranie.algorithms.utils.Optimizable;

import java.util.ArrayList;
import java.util.List;

/**
 * 桶排序
 *
 * @author dingdong
 * @since 2021/4/23
 */
@Optimizable
public class BinSort implements IntSort {

    @Override
    public String name() {
        return "桶排序";
    }

    /**
     * 排序数组，数组中每个元素都不为空！
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }
        if (max == min) {
            return; // 已排序
        }
        // 计算桶数量
        int bucketSize = (max - min) / a.length + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketSize);
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(new ArrayList<>());
        }
        // 映射
        for (int e : a) {
            int index = (e - min) / a.length;
            buckets.get(index).add(e);
        }
        // 对每个桶进行排序
        int index = 0;
        for (List<Integer> bucket : buckets) {
            int[] a1 = bucket.stream().mapToInt(t -> t).toArray();
            Insertion.sort(a1, 0, a1.length - 1);
            System.arraycopy(a1, 0, a, index, a1.length);
            index += a1.length;
        }
    }
}
