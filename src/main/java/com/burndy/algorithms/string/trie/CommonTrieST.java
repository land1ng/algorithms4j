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
    public void del(String key) {
        root = del(root, key, 0);
    }

    private Node<V> del(Node<V> x, String key, int depth) {
        if (x == null) {
            return null;
        }
        if (depth == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(depth);
            Node<V> n = del(x.next.get(c), key, depth + 1);
            if (n == null) {
                x.next.remove(c);
            } else {
                x.next.put(c, n);
            }
        }
        if (x.val != null) {
            return x;
        }
        return x.next.isEmpty() ? null : x;
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
        return search(x.next.get(c), s, depth + 1, length);
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
        trie.del("中国");
        trie.del("中国人名");
        trie.del("中国长城");
//        trie.del("中国西安");
        System.out.println(trie.keys("中国"));
    }
}
