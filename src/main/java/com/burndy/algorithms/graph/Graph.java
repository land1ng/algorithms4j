package com.burndy.algorithms.graph;

/**
 * 无向图
 *
 * @author dranfree
 * @since 2020.03.12
 */
public interface Graph {

    // 顶点数
    int V();

    // 边数
    int E();

    // 向图中添加一条边 v-w
    void addEdge(int v, int w);

    // 和 v 相邻的所有顶点
    Iterable<Integer> adj(int v);
}