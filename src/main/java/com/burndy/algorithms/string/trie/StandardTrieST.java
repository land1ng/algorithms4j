package com.burndy.algorithms.string.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * 标注单词查找树
 *
 * @author RanYeah
 * @since 2022/8/28
 */
public class StandardTrieST<V> implements TrieST<V> {

    private static final int R = 256;

    private Node<V> root;

    @SuppressWarnings("unchecked")
    private static class Node<V> {

        private V val;

        private final Node<V>[] next = (Node<V>[]) new Node[R];
    }

    @Override
    public V get(String key) {
        Node<V> x = get(root, key, 0);
        return x == null ? null : x.val;
    }

    private Node<V> get(Node<V> x, String key, int depth) {
        // 返回以x作为根结点的子单词查找树中与key相关联的值
        if (x == null) {
            return null;
        }
        if (depth == key.length()) {
            return x;
        }
        // 找到第d个字符所对应的子单词查找树
        char c = key.charAt(depth);
        return get(x.next[c], key, depth + 1);
    }

    @Override
    public void del(String key) {
        root = del(root, key, 0);
    }

    private Node<V> del(Node<V> x, String key, int depth) {
        if (x == null) {
            return null;
        }
        if (depth == key.length()) {
            // 将等于key的那个结点置为空
            x.val = null;
        } else {
            char c = key.charAt(depth);
            x.next[c] = del(x.next[c], key, depth + 1);
        }
        if (x.val != null) {
            return x;
        }
        // 如果x的链接有一个不为空，那么原样返回x结点。
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        // 如果它的所有链接均为空，那就需要从数据结构中删去这个结点。
        // 如果删去它使得它的父结点的所有链接也均为空，就需要继续删除它的父结点，以此类推。
        return null;
    }

    @Override
    public void add(String key, V val) {
        this.root = add(root, key, val, 0);
    }

    private Node<V> add(Node<V> x, String key, V val, int depth) {
        // 如果key存在于以x为根结点的子单词查找树中则更新于它相关联的值
        if (x == null) {
            x = new Node<>();
        }
        if (depth == key.length()) {
            x.val = val;
            return x;
        }
        // 找到第d个字符所对应的子单词查找树
        char c = key.charAt(depth);
        x.next[c] = add(x.next[c], key, val, depth + 1);
        return x;
    }

    @Override
    public List<String> keys(String pre) {
        List<String> res = new ArrayList<>();
        collect(get(root, pre, 0), pre, res);
        return res;
    }

    private void collect(Node<V> x, String pre, List<String> res) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            res.add(pre);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, res);
        }
    }

    @Override
    public String longestPrefix(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node<V> x, String s, int depth, int length) {
        if (x == null) {
            return length;
        }
        if (x.val != null) {
            length = depth;
        }
        if (depth == s.length()) {
            return length;
        }
        char c = s.charAt(depth);
        return search(x.next[c], s, depth + 1, length);
    }

    public static void main(String[] args) {
        TrieST<Integer> trie = new StandardTrieST<>();
        trie.add("by", 1);
        trie.add("sea", 1);
        trie.add("sells", 1);
        trie.add("she", 1);
        trie.add("shells", 1);
        trie.add("shore", 1);
        trie.add("the", 1);
        System.out.println(trie.keys("sh"));
        trie.del("she");
        trie.del("shells");
        trie.del("shore");
        System.out.println(trie.keys("sh"));
    }
}
