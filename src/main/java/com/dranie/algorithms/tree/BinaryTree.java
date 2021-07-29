package com.dranie.algorithms.tree;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
public class BinaryTree<K extends Comparable<K>, V> implements Tree<K, V> {

    private Node<K, V> root;

    @Getter
    @RequiredArgsConstructor
    private static class Node<K extends Comparable<K>, V> implements Tree.Node<K, V> {

        private final K key;

        private V value;

        private Node<K, V> left;
        private Node<K, V> right;

        // for iterate
//        private Node<K, V> prev;
        private Node<K, V> next;

        public V setValue(V newVal) {
            final V oldValue = this.value;
            this.value = newVal;
            return oldValue;
        }

        public static <K extends Comparable<K>, V> Node<K, V> create(K key, V value) {
            Node<K, V> node = new Node<>(key);
            node.value = value;
            return node;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.getValue();
    }

    private Node<K, V> getNode(Node<K, V> root, K key) {
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
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        return putVal(key, value);
    }

    // return new node or old matched node
    @Deprecated
    private Node<K, V> put(Node<K, V> root, K key, V value) {
        if (root == null) {
            return Node.create(key, value);
        }
        int cmp = root.getKey().compareTo(key);
        if (cmp == 0) {
            root.setValue(value);
        }
        if (cmp > 0) {
            root.left = put(root.left, key, value);
        } else {
            root.right = put(root.right, key, value);
        }
        return root;
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
    public int size() {
        return size(root);
    }

    private int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return size(node.left) + size(node.right) + 1;
    }

    @Override
    public K minKey() {
        Node<K, V> node = min(root);
        return node == null ? null : node.getKey();
    }

    private Node<K, V> min(Node<K, V> root) {
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
        Node<K, V> node = max(root);
        return node == null ? null : node.getKey();
    }

    private Node<K, V> max(Node<K, V> root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        }
        return max(root.right);
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
    public void deleteKey(K key) {
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
    public boolean containsKey(K key) {
        return getNode(this.root, key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        // TODO 暂不实现
        throw new UnsupportedOperationException();
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
