package com.dranie.algorithms.graph;

import com.dranie.algorithms.Utils;
import com.dranie.algorithms.utils.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * 图相关测试
 *
 * @author dranfree
 * @since 2020.03.13
 */
@Slf4j
public class GraphTests {

    @Test
    public void testGraphBuild() {
        In in = new In(Utils.locateSampleFile("tinyG.txt"));
        Graph G = new GraphImpl(in);
        log.debug("Tiny Graph: {}", G);
    }

    /**
     * 广度优先搜索测试
     */
    @Test
    public void testFindShortestPath() {
        In in = new In(Utils.locateSampleFile("tinyCG.txt"));
        Graph G = new GraphImpl(in);
        log.debug("Tiny Graph: {}", G);
        // 获取从顶点0可到达的所有路径
        Paths depthPaths = new PathsDepthFirst(G, 0);
        log.debug("depthPaths: {}", depthPaths.listPathsTo(3));
        // 获取从顶点0可到达的所有最短路径
        Paths breadthPaths = new PathsBreadthFirst(G, 0);
        log.debug("breadthPaths: {}", breadthPaths.listPathsTo(3));
    }
}