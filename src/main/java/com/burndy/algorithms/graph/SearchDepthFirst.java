package com.burndy.algorithms.graph;

/**
 * 深度优先搜索：找到和某顶点连通的所有顶点
 *
 * @author dranfree
 * @since 2020.06.02
 */
public class SearchDepthFirst implements Search {

    private final boolean[] marked;
    private int count;

    /**
     * 找到和 s 连通的所有顶点
     *
     * @param G
     * @param s
     */
    public SearchDepthFirst(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        // 标记 v 这个顶点已走过
        marked[v] = true;
        count++; // 和 v 相邻的顶点 +1
        // 遍历和 v 相邻的所有顶点，如果某顶点尚未走过，那么递归标记。
        G.adj(v).forEach(w -> {
            if (!marked[w])
                dfs(G, w);
        });
        // 最后，标记过的顶点一定是相邻的，对应的 marked = true 。
    }

    /**
     * 测试 v 和 s 是连通的吗
     *
     * @param v
     * @return
     */
    @Override
    public boolean marked(int v) { return marked[v]; }

    /**
     * 与 s 连通的顶点总数
     *
     * @return
     */
    @Override
    public int count() { return count; }
}