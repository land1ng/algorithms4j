package com.dranie.algorithms.sort.topk;

import java.io.File;

/**
 * 查询某个数据集中最大或最小的数据集合
 *
 * @author dran
 * @since 2020-06-04
 */
@FunctionalInterface
public interface TopK {

    /**
     * 从数据文件中提取数据并过滤最大或最小的 k 个数据
     *
     * @param source
     * @param k
     * @return 最小的k个整数
     */
    int[] fetch(File source, int k);

}
