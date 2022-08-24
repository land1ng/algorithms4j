package com.burndy.algorithms.graph;

import java.util.*;

/**
 * 广度优先搜索：寻找单点最短路径
 *
 * @author dran
 * @since 2020-08-31
 */
public class PathsBreadthFirst implements Paths {

    private final boolean[] marked; // 到达该顶点的最短路径已知吗？
    private final int[]     edgeTo; // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;      // 起点

    public PathsBreadthFirst(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    /**
     * 广度优先搜索
     *
     * 寻找过程：要找到从s到v的最短路径，从s开始，在所有由一条边就可以到达的顶点中寻找v，如果找不到
     * 我们就继续在与s距离两条边的所有顶点中查找v，如此一直进行。
     *
     * 比较深度优先搜索：深度优先搜索像是一个人在走迷宫，广度优先搜索则好像是一组人在朝各个方向走这座迷宫。
     *
     * @param G
     * @param s 搜索起点
     */
    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true; // 标记起点
        queue.offer(s);   // 将它加入队列
        while (!queue.isEmpty()) {
            int v = queue.poll();       // 从队列中删去下一顶点
            G.adj(v).forEach(w -> {
                if (!marked[w]) {       // 对于每个未被标记的相邻顶点
                    edgeTo[w] = v;      // 保存最短路径的最后一条边
                    marked[w] = true;   // 标记它，因为最短路径已知（最早经过的路径就是最短路径）
                    queue.offer(w);     // 并将它添加到队列中
                }
            });
        }
    }

    /**
     * 是否存在从 s 到 v 的路径
     *
     * @param v
     * @return
     */
    @Override
    public boolean hasPathTo(int v) { return marked[v]; }

    /**
     * 列出所有 s 到 v 的路径
     *
     * 返回完整的一条路径，形如：
     *
     * 0 to 0: 0
     * 0 to 1: 0-1
     * 0 to 2: 0-2
     * 0 to 3: 0-2-3
     * 0 to 4: 0-2-4
     * 0 to 5: 0-5
     *
     * @param v
     * @return
     */
    @Override
    public Iterable<Integer> listPathsTo(int v) {
        // 同深度优先搜索
        return listPathFromS2V(edgeTo, s, v);
    }
}
