package com.dranie.algorithms.graph;

/**
 * 图处理算法：计算连通性：深度优先搜索
 *
 * @author dranfree
 * @since 2020.06.01
 */
public interface Search {

    /**
     * 测试 v 和 s 是连通的吗
     *
     * @param v
     * @return
     */
    boolean marked(int v);

    /**
     * 与 s 连通的顶点总数
     *
     * @return
     */
    int count();
}