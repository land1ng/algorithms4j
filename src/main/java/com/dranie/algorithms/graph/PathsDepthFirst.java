package com.dranie.algorithms.graph;

/**
 * 深度优先搜索
 *
 * @author dranfree
 * @since 2020.06.02
 */
public class PathsDepthFirst implements Paths {



    /**
     * 是否存在从 s 到 v 的路径
     *
     * @param v
     * @return
     */
    @Override
    public boolean hasPathTo(int v) {
        return false;
    }

    /**
     * 列出所有 s 到 v 的路径，如果不存在则返回 null 。
     *
     * @param v
     * @return
     */
    @Override
    public Iterable<Integer> listPathsTo(int v) {
        return null;
    }
}