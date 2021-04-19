package com.dranie.algorithms.graph;

/**
 * 寻找存在的路径：深度优先搜索
 *
 * 深度优先搜索和union-find算法孰优孰劣：
 * 1.理论上，深度优先搜索更快，因为它能保证所需的时间是常数级别；
 * 2.实际上，union-find更快，因为它不需要完整地构造并表示一幅图；更重要的是，union-find是一种动态算法（我们在任何时候都
 *   能用接近常数的时间检查两个顶点是否连通，甚至是在添加一条边的时候），但是深度优先搜索则必须要对图进行预处理。
 *
 * TODO 什么意思？
 * 抉择：我们在完成只需要判断连通性或是需要完成大量连通性查询和插入操作混合等类似的任务时，更倾向于使用union-find算法，
 * 但深度优先搜索则更适合实现图的抽象数据类型，因为它能更有效地利用已有的数据结构。
 *
 * 问题：
 * 1.给定的图是无环图吗？
 * 2.给定的图是二分图吗？或者，能够用两种颜色将图的所有顶点着色，使得任意一条边的两个端点的颜色都不相同吗？
 *
 * @author dranfree
 * @since 2020.06.02
 */
public class PathsDepthFirst implements Paths {

    private final boolean[] marked; // 这个顶点上调用过dfs()了吗？
    private final int[] edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;      // 起点

    public PathsDepthFirst(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    /**
     * 深度优先搜索
     *
     * @param G ~
     * @param v ~
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        G.adj(v).forEach(w -> {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        });
    }

    /**
     * 是否存在从 s 到 v 的路径
     *
     * @param v ~
     * @return ~
     */
    @Override
    public boolean hasPathTo(int v) { return marked[v]; }

    /**
     * 列出所有 s 到 v 的路径
     *
     * @param v ~
     * @return ~
     */
    @Override
    public Iterable<Integer> listPathsTo(int v) {
        return listPathFromS2V(edgeTo, s, v);
    }
}