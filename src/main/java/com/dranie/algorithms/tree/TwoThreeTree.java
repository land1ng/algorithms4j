package com.dranie.algorithms.tree;

import java.util.Iterator;

/**
 * 2-3 Tree
 * <p>
 * TODO
 *
 * @author ran.ding
 * @since 2021/7/29
 */
public class TwoThreeTree<K extends Comparable<K>, V> implements Tree<K, V> {

    @Override
    public V get(K key) {
        return null;
    }

    /**
     * 插入新键的几种情况：
     * <p>
     * 1.向2-结点中插入
     * <p>
     * - 2-结点直接替换为3-结点即可
     * <p>
     * 2.向一棵只含有3-结点的树中插入新键
     * <p>
     * - 3-结点变4-结点再分解为三个2-结点
     * <p>
     * 3.向一个父结点为2-结点的3-结点中插入新键
     * <p>
     * - 3-结点变4-结点
     * <p>
     * - 4-结点分解为2个2-结点并将中键移入父结点中
     * <p>
     * 4.向一个父结点为3-结点的3-结点中插入新键
     * <p>
     * - 类似上一种情况，但递归向上分解。
     * <p>
     * - 最后可能会分解根结点
     *
     * @param key   ~
     * @param value ~
     * @return
     */
    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public K minKey() {
        return null;
    }

    @Override
    public K maxKey() {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public void deleteKey(K key) {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Iterator<Tree.Node<K, V>> iterator() {
        return null;
    }


}
