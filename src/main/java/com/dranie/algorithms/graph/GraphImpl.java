package com.dranie.algorithms.graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * 无向图的邻接表数组实现
 *
 * @author dranfree
 * @since 2020.03.13
 */
@SuppressWarnings("unchecked")
public class GraphImpl implements Graph {

    private final int V;        // 顶点数目
    private int E;              // 边的数目
    private Bag<Integer>[] adj; // 邻接表

    public GraphImpl(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public GraphImpl(In in) {
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
    public int v() { return V; }
    @Override
    public int e() { return E; }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    @Override
    public Iterable<Integer> adj(int v) { return adj[v]; }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(v()).append(" vertices, ").append(e()).append(" edges").append(System.lineSeparator());
        for (int v = 0; v < v(); v++) {
            sb.append(v).append(": ");
            for (int w : this.adj(v))
                sb.append(w).append(" ");
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}