package com.dranie.algorithms.graph;

/**
 * 常用图处理代码
 *
 * @author dranfree
 * @since 2020.03.12
 */
public abstract class GraphUtil {

    /**
     * 计算 v 的度数（依附某个顶点的边的总数）
     *
     * @param G
     * @param v
     * @return
     */
    public static int degree(Graph G, int v) {
        int degree = 0;
        for (int w : G.adj(v))
            degree++;
        return degree;
    }

    /**
     * 计算所有顶点的最大度数
     *
     * @param G
     * @return
     */
    public static int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            int current = degree(G, v);
            if (current > max)
                max = current;
        }
        return max;
    }

    /**
     * 计算所有顶点的平均度数
     *
     * @param G
     * @return
     */
    public static double avgDegree(Graph G) {
        return 2.0 * G.E() / G.V();
    }

    /**
     * 计算自环的个数
     *
     * @param G
     * @return
     */
    public static int countSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w)
                    count++;
            }
        }
        return count / 2; // 每条边被记录了两次，比如 v-w 记了一次，w-v 又被记录了一次。
    }
}