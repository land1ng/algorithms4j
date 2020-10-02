package com.dranie.algorithms.graph.directional;

import com.dranie.algorithms.utils.In;

import java.util.LinkedList;
import java.util.List;

/**
 * @author dranfree
 * @since 2020.09.06
 */
public class DigraphImpl implements Digraph {

    private final int V;
    private int       E;
    private final List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public DigraphImpl(int V) {
        this.V = V;
        this.E = 0;
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<>();
    }

    public DigraphImpl(In in) {
        // 读取 V 并将图初始化
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt(); // 先读取一个顶点
            int w = in.readInt(); // 再读取一个顶点
            addEdge(v, w);
        }
    }

    @Override
    public int V() { return V; }

    @Override
    public int E() { return E; }

    @Override
    public void addEdge(int v, int w) {
        // 和无向图的区别主要就在这儿，只会维护单项路径。
        adj[v].add(w);
        E++;
    }

    @Override
    public Iterable<Integer> adj(int v) { return adj[v]; }

    /**
     * 该图的反向图
     *
     * @return
     */
    @Override
    public Digraph reverse() {
        Digraph R = new DigraphImpl(V());
        for (int v = 0; v < V(); v++)
            for (int w : adj(v))
                R.addEdge(w, v);
        return R;
    }
}