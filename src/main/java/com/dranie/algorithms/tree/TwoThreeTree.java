package com.dranie.algorithms.tree;

import lombok.experimental.Delegate;

/**
 * 2-3 Tree
 *
 * @author ran.ding
 * @since 2021/7/29
 */
public class TwoThreeTree<K extends Comparable<K>, V> implements Tree<K, V> {

    @Delegate
    private final Tree<K, V> delegate = new RedBlackTree<>();

}
