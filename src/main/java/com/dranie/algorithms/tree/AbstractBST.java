package com.dranie.algorithms.tree;

import lombok.Getter;

/**
 * @author ran.ding
 * @since 2021/7/29
 */
public abstract class AbstractBST<K extends Comparable<K>, V, S extends AbstractBST.Node<K, V, S>> implements Tree<K, V> {

    protected abstract S getRoot();

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        S node = getNode(getRoot(), key);
        return node == null ? null : node.getValue();
    }

    private S getNode(S root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = root.getKey().compareTo(key);
        if (cmp == 0) {
            return root;
        }
        if (cmp > 0) {
            return getNode(root.left, key);
        } else {
            return getNode(root.right, key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(getRoot(), key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO 暂不实现
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size(getRoot());
    }

    private int size(S node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    @Override
    public K minKey() {
        S node = min(getRoot());
        return node == null ? null : node.getKey();
    }

    protected S min(S root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        }
        return min(root.left);
    }

    @Override
    public K maxKey() {
        S node = max(getRoot());
        return node == null ? null : node.getKey();
    }

    protected S max(S root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return max(root.right);
    }

    @Getter
    abstract static class Node<K extends Comparable<K>, V, S extends Node<K, V, S>> implements Tree.Node<K, V> {

        protected final K key;

        protected V value;

        protected S left;
        protected S right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V setValue(V newVal) {
            final V oldValue = this.value;
            this.value = newVal;
            return oldValue;
        }
    }
}
