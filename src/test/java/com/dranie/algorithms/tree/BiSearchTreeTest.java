package com.dranie.algorithms.tree;

/**
 * @author ran.ding
 * @since 2021/7/29
 */
public class BiSearchTreeTest extends TreeTest {

    @Override
    protected Tree<String, String> getEmptyTree() {
        return new BiSearchTree<>();
    }
}
