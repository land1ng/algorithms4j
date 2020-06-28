package com.dranie.algorithms.graph;

import com.dranie.algorithms.Utils;
import edu.princeton.cs.algs4.In;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
}