package com.dranie.algorithms.graph;

/**
 * 深度优先搜索：是否是一个无环图？
 *
 * @author dran
 * @since 2020-09-02
 */
public class CycleGraph {

    private boolean[] marked;
    private boolean   hasCycle;

    public CycleGraph(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++)
            if (!marked[s])
                dfs(G, s, s);
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w, v);
            else if (w != u) hasCycle = true;
    }

    public boolean hasCycle() { return hasCycle; }
}
