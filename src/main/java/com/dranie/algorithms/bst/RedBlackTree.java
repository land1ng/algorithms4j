package com.dranie.algorithms.bst;

import java.util.Iterator;

/**
 * 红黑树的性质：
 * <ul>
 *     <li>基本思想是：用标准二叉树(完全由2-结点构成)和一些额外信息(替换3-结点)来表示2-3树</li>
 *     <li>红黑树是2-3树的一种实现方式</li>
 *     <li>根节点总是黑色</li>
 *     <li>红链接将2个2-结点连接起来构成一个3-结点，黑链接则是2-3树中的普通结点。</li>
 *     <li>红链接均为左链接</li>
 *     <li>没有任何一个结点同时和两条红链接相连</li>
 *     <li>该树是完美黑色平衡的，即任意空链接到根结点额路径上的黑链接数量相同。</li>
 *     <li>将红黑树画平时，一棵红黑树就是一棵2-3树。<p><image src="https://ding-resource.oss-cn-hangzhou.aliyuncs.com/flat_red_black_tree.png"/></li>
 *     <li>使用随机键构建的一棵典型红黑树：<p><image src="https://ding-resource.oss-cn-hangzhou.aliyuncs.com/random_red_black_tree.jpg" /></li>
 *     <li>一棵大小为{@code N}的红黑树的高度不会超过{@code 2lgN}</li>
 * </ul>
 * 使用标准二叉树的思想来实现2-3树的好处是，我们可以直接使用标准二叉树的 {@code get()} 方法而无需任何修改。
 * <p>
 * 一些疑问：
 * <ul>
 *     <li>为什么只允许红色左链接？<p>
 *         可以减少可能出现的各种情况，因此用来实现的代码会更少。</li>
 *     <li>为什么不在Node中使用K数组来保存2-结点/3-结点/4-结点？<p>
 *         B-Tree中是这样做的，但是2-3树中结点较少，用数组带来的开销过大。</li>
 * </ul>
 * <p>
 *
 * @author ran.ding
 * @since 2021/7/29
 */
public class RedBlackTree<K extends Comparable<K>, V>
        extends AbstractBST<K, V, RedBlackTree.Node<K, V>> implements Tree<K, V> {

    private Node<K, V> root;

    @Override
    protected Node<K, V> getRoot() {
        return root;
    }

    @Override
    public V put(K key, V value) {
        this.root = put(this.root, key, value);
        this.root.toBlack();
        // fixme 需要返回旧值
        return null;
    }

    private Node<K, V> put(Node<K, V> h, K key, V value) {
        if (h == null) {
            Node<K, V> node = new Node<>(key, value);
            node.toRed();
            return node;
        }
        int cmp = h.getKey().compareTo(key);
        if (cmp > 0) {
            h.left = put(h.left, key, value);
        } else if (cmp < 0) {
            h.right = put(h.right, key, value);
        } else {
            h.setValue(value);
        }
        // colors
        // 1.左链接是黑色，右链接是红色。
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        // 2.左链接是红色，左链接的左链接也是红色。（连续两个红色链接）
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // 3.左链接和右链接都是红色
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    private boolean isRed(Node<K, V> node) {
        return node != null && node.isRed();
    }

    // 将该结点颜色转换为红色，两个子结点颜色变为黑色。
    private void flipColors(Node<K, V> node) {
        node.toRed();
        node.left.toBlack();
        node.right.toBlack();
    }

    /**
     * 左旋转h的右链接
     * <p>
     * <image src="https://ding-resource.oss-cn-hangzhou.aliyuncs.com/RBT_rotateLeft.jpg" />
     *
     * @param h 待旋转链接的父结点
     * @return 旋转过后的父结点
     */
    private Node<K, V> rotateLeft(Node<K, V> h) {
        Node<K, V> x = h.right;
        h.right = x.left;
        x.left = h;
        x.toColor(h.color);
        h.toRed();
        return x;
    }

    /**
     * 右旋转h的左链接
     * <p>
     * <image src="https://ding-resource.oss-cn-hangzhou.aliyuncs.com/RBT_rotateRight.jpg" />
     *
     * @param h 待旋转链接的父结点
     * @return 旋转过后的父结点
     */
    private Node<K, V> rotateRight(Node<K, V> h) {
        Node<K, V> x = h.left;
        h.left = x.right;
        x.right = h;
        x.toColor(h.color);
        h.toRed();
        return x;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    /**
     * 删除最小键
     * <p>
     * 1.
     */
    @Override
    public void deleteMin() {
        // TODO
    }

    @Override
    public void deleteMax() {
        // TODO
    }

    @Override
    public void delete(K key) {
        // TODO
    }

    @Override
    public Iterator<Tree.Node<K, V>> iterator() {
        // TODO 前序遍历、中序遍历、后序遍历
        return null;
    }

    static class Node<K extends Comparable<K>, V>
            extends AbstractBST.Node<K, V, Node<K, V>>
            implements Tree.Node<K, V> {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private boolean color; // 父结点指向此结点的链接颜色

        public Node(K key, V value) {
            super(key, value);
        }

        public boolean isRed() {
            return color == RED;
        }

        public boolean isBlack() {
            return color == BLACK;
        }

        public void toRed() {
            this.color = RED;
        }

        public void toBlack() {
            this.color = BLACK;
        }

        public void toColor(boolean color) {
            this.color = color;
        }

        public void flipColor() {
            this.color = !this.color;
        }
    }
}
