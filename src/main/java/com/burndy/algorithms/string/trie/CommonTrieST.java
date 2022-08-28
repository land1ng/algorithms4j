package com.burndy.algorithms.string.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RanYeah
 * @since 2022/8/28
 */
public class CommonTrieST<V> implements TrieST<V> {

    private Node<V> root;

    private static class Node<V> {

        private V val;

        private final Map<Character, Node<V>> next = new HashMap<>();
    }

    @Override
    public V get(String key) {
        Node<V> x = get(root, key, 0);
        return x == null ? null : x.val;
    }

    private Node<V> get(Node<V> x, String key, int depth) {
        if (x == null) {
            return null;
        }
        if (depth == key.length()) {
            return x;
        }
        char c = key.charAt(depth);
        return get(x.next.get(c), key, depth + 1);
    }

    @Override
    public void add(String key, V val) {
        root = add(root, key, val, 0);
    }

    private Node<V> add(Node<V> x, String key, V val, int depth) {
        if (x == null) {
            x = new Node<>();
        }
        if (depth == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(depth);
        x.next.put(c, add(x.next.get(c), key, val, depth + 1));
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
        x.next.forEach((c, n) -> collect(n, pre + c, res));
    }

    public static void main(String[] args) {
        TrieST<String> trie = new CommonTrieST<>();
        trie.add("中国", "中国");
        trie.add("中国人名", "中国");
        trie.add("中华人民", "中国");
        trie.add("中华人民共和国", "中国");
        trie.add("中国长城", "中国");
        trie.add("中国西安", "中国");
        System.out.println(trie.keys("中国"));
    }
}
