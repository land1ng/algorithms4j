package com.dranie.algorithms.bst;

/**
 * @author ran.ding
 * @since 2021/7/29
 */
public class RedBlackTreeTest extends TreeTest {

    @Override
    protected <K extends Comparable<K>, V> Tree<K, V> getEmptyTree() {
        return new RedBlackTree<>();
    }
}
