package com.dranie.algorithms.bst;

import lombok.Getter;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 二叉查找树
 * <li>
 *     <ul>key is not null</ul>
 *     <ul>value is nullable</ul>
 * </li>
 *
 * @author ran.ding
 * @since 2021/7/29
 */
public class BiSearchTree<K extends Comparable<K>, V>
        extends AbstractBST<K, V, BiSearchTree.Node<K, V>> implements Tree<K, V> {

    private Node<K, V> root;

    protected Node<K, V> getRoot() {
        return root;
    }

    @Getter
    static class Node<K extends Comparable<K>, V> extends AbstractBST.Node<K, V, Node<K, V>> implements Tree.Node<K, V> {

        // for iterate
//        private Node<K, V> prev;
        private Node<K, V> next;

        public Node(K key, V value) {
            super(key, value);
        }

        public static <K extends Comparable<K>, V> Node<K, V> create(K key, V value) {
            return new Node<>(key, value);
        }
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        return putVal(key, value);
    }

    // put value and return old value if present
    private V putVal(K key, V value) {
        if (this.root == null) {
            // add first node
            this.root = Node.create(key, value);
        } else {
            Node<K, V> node = this.root;
            do {
                int cmp = node.getKey().compareTo(key);
                if (cmp == 0) {
                    // update
                    return node.setValue(value);
                }
                if (cmp > 0) {
                    if (node.left == null) {
                        node.left = Node.create(key, value);
                        node.left.next = node;
                        break;
                    }
                    node = node.left;
                } else {
                    if (node.right == null) {
                        node.right = Node.create(key, value);
                        // update node's next and node.right's next
                        Node<K, V> oldNext = node.next;
                        node.next = node.right;
                        node.right.next = oldNext;
                        break;
                    }
                    node = node.right;
                }
            } while (true);
        }
        return null;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public void deleteMin() {
        this.root = deleteMin(this.root);
    }

    private Node<K, V> deleteMin(Node<K, V> x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public void deleteMax() {
        this.root = deleteMax(this.root);
    }

    private Node<K, V> deleteMax(Node<K, V> x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        return x;
    }

    /**
     * 1.若待删除的节点只有一个子节点，那么
     *
     * @param key
     */
    @Override
    public void delete(K key) {
        this.root = deleteKey(this.root, key);
    }

    private Node<K, V> deleteKey(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = root.getKey().compareTo(key);
        if (cmp == 0) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            Node<K, V> t = root;
            root = min(t.right); // 用右子树最小节点替换
            root.right = deleteMin(root.right);
            root.left = t.left;
            return root;
        }
        if (cmp > 0) {
            root.left = deleteKey(root.left, key);
        } else {
            root.right = deleteKey(root.right, key);
        }
        return root;
    }

    @Override
    public Iterator<Tree.Node<K, V>> iterator() {
        return new It<>(root);
    }

    private static class It<K extends Comparable<K>, V> implements Iterator<Tree.Node<K, V>> {

        private Node<K, V> next;

        public It(Node<K, V> root) {
            Node<K, V> current = root;
            while (current != null && current.left != null) {
                current = current.left;
            }
            this.next = current;
        }

        @Override
        public Tree.Node<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<K, V> next = this.next;
            this.next = this.next.next;
            return next;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

    }

}
