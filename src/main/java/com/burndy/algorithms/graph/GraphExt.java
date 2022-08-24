package com.burndy.algorithms.graph;

/**
 * 无向图扩展（邻接集）：
 * 1.添加一个顶点
 * 2.删除一个顶点
 * 3.删除一条边
 * 4.检查图中是否含有边 v-w
 *
 * @author dranfree
 * @since 2020.06.01
 */
public interface GraphExt extends Graph {
    void    addVertex(int v);
    void    delVertex(int v);
    void    delEdge(int v, int w);
    boolean hasEdge(int v, int w);
}