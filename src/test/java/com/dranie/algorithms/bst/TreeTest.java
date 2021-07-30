package com.dranie.algorithms.bst;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ran.ding
 * @since 2021/7/29
 */
abstract class TreeTest {

    @Test
    void test() {
        Tree<Integer, Integer> tree = getEmptyTree();
        assertTrue(tree.isEmpty());
        int minKey = Integer.MAX_VALUE;
        int maxKey = Integer.MIN_VALUE;
        Map<Integer, Integer> nc = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int key = ThreadLocalRandom.current().nextInt(50);
            nc.put(key, i);
            tree.put(key, i);
            minKey = Math.min(minKey, key);
            maxKey = Math.max(maxKey, key);
        }
        assertNull(tree.get(100));
        assertEquals(nc.get(1), tree.get(1));
        assertEquals(tree.size(), nc.size());
        assertEquals(tree.minKey(), minKey);
        assertEquals(tree.maxKey(), maxKey);
        tree.deleteMin();
        tree.deleteMax();
        assertTrue(tree.minKey() >= minKey);
        assertTrue(tree.maxKey() <= maxKey);
        tree.deleteKey(20);
        assertNull(tree.get(20));
    }

    protected abstract <K extends Comparable<K>, V> Tree<K, V> getEmptyTree();
}
