package com.burndy.algorithms.graph;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

/**
 * 图处理算法：寻找路径
 * <p>
 * 广度优先搜索和深度优先搜索的区别：
 * 1.深度优先搜索：栈
 * 2.广度优先搜索：队列
 *
 * @author dranfree
 * @since 2020.06.02
 */
public interface Paths {

    /**
     * 是否存在从 s 到 v 的路径
     *
     * @param v
     * @return
     */
    boolean hasPathTo(int v);

    /**
     * 列出所有 s 到 v 的路径，如果不存在则返回 null 。
     *
     * @param v
     * @return
     */
    Iterable<Integer> listPathsTo(int v);

    default Iterable<Integer> listPathFromS2V(int[] edgeTo, int s, int v) {
        if (!hasPathTo(v)) return Collections.emptyList();
        Deque<Integer> path = new ArrayDeque<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}