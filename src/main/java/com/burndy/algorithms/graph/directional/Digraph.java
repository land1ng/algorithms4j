package com.burndy.algorithms.graph.directional;

import com.burndy.algorithms.graph.Graph;

/**
 * 有向图
 *
 * @author dranfree
 * @since 2020.09.06
 */
public interface Digraph extends Graph {

    // 为有向图添加一条边：v -> w
    @Override
    void addEdge(int v, int w);

    /**
     * 该图的反向图
     *
     * @return
     */
    Digraph reverse();
}