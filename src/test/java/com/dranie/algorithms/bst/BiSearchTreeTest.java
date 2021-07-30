package com.dranie.algorithms.bst;

/**
 * @author ran.ding
 * @since 2021/7/29
 */
public class BiSearchTreeTest extends TreeTest {

    @Override
    protected <K extends Comparable<K>, V> Tree<K, V> getEmptyTree() {
        return new BiSearchTree<>();
    }
}
