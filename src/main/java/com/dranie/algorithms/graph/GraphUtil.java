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
    public static int degree(GraphImpl G, int v) {
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
    public static int maxDegree(GraphImpl G) {
        int max = 0;
        for (int v = 0; v < G.v(); v++) {
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
    public static double avgDegree(GraphImpl G) {
        return 2.0 * G.e() / G.v();
    }

    /**
     * 计算自环的个数
     *
     * @param G
     * @return
     */
    public static int countSelfLoops(GraphImpl G) {
        int count = 0;
        for (int v = 0; v < G.v(); v++) {
            for (int w : G.adj(v)) {
                if (v == w)
                    count++;
            }
        }
        return count / 2; // 每条边被记录了两次，比如 v-w 记了一次，w-v 又被记录了一次。
    }
}