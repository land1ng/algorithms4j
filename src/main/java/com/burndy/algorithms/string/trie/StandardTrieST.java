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

    public static void main(String[] args) {
        TrieST<Integer> trie = new StandardTrieST<>();
        trie.add("by", 1);
        trie.add("sea", 1);
        trie.add("sells", 1);
        trie.add("she", 1);
        trie.add("shells", 1);
        trie.add("shore", 1);
        trie.add("the", 1);
        System.out.println(trie.keys("s"));
    }
}
