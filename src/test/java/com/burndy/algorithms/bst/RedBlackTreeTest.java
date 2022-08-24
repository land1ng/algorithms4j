package com.burndy.algorithms.bst;

import org.junit.jupiter.api.Test;

/**
 * @author ran.ding
 * @since 2021/7/29
 */
public class RedBlackTreeTest extends TreeTest {

    @Override
    protected <K extends Comparable<K>, V> Tree<K, V> getEmptyTree() {
        return new RedBlackTree<>();
    }

    @Test
    void testOldValue() {
        Tree<String, String> tree = new RedBlackTree<>();
        String oldValue1 = tree.put("1", "2");
        System.out.println(oldValue1);
        String oldValue2 = tree.put("1", "3");
        System.out.println(oldValue2);
    }
}
