package com.burndy.algorithms.lru;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ran.ding
 * @since 2021/9/27
 */
public class SimpleLRU<K, V> implements LRU<K, V> {

    private Node<K, V> head;

    private Node<K, V> tail;

    private int size;

    private final int capacity;

    public SimpleLRU(int capacity) {
        this.capacity = capacity;
        if (this.capacity <= 1) {
            throw new IllegalArgumentException("at least 1 element");
        }
    }

    @RequiredArgsConstructor
    private static class Node<K, V> {

        private final K key;
        private V val;

        private Node<K, V> prev;
        private Node<K, V> next;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = findNode(key);
        moveToHead(node); // 移动到链表表头
        return node == null ? null : node.val;
    }

    @Override
    public void add(K key, V val) {
        Node<K, V> oldNode = findNode(key);
        if (oldNode != null) {
            oldNode.val = val;
            moveToHead(oldNode);
        } else {
            // insert new to head
            Node<K, V> newNode = new Node<>(key);
            newNode.val = val;
            newNode.next = head;
            if (head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                head.prev = newNode;
                this.head = newNode; // set to head
            }
            this.size++;
            evictIfOverflow();
        }
    }

    private void evictIfOverflow() {
        if (this.size > this.capacity) {
            // remove tail
            // assert size >= 2
            Node<K, V> newTail = tail.prev;
            newTail.next = null;
            tail.prev = null;
            tail.next = null; // not necesssary
            tail = newTail;
            this.size--;
        }
    }

    private Node<K, V> findNode(K key) {
        if (head == null) {
            return null;
        }
        Node<K, V> current = head;
        do {
            if (Objects.equals(key, current.key)) {
                break;
            }
            current = current.next;
        } while (current != null);
        return current;
    }

    private void moveToHead(Node<K, V> node) {
        if (node == null) {
            return;
        }
        Node<K, V> prev = node.prev;
        Node<K, V> next = node.next;
        if (prev != null) {
            // 在中间或者末尾
            // 1.从原位置移除
            doRemove(node);
            // 2.放到链表表头
            node.prev = null;
            node.next = head;
            head.prev = node;
            this.head = node;
        } // 否则，就是链表表头，不管了
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void remove(K key) {
        doRemove(findNode(key));
        this.size--;
    }

    private void doRemove(Node<K, V> node) {
        if (node == null) {
            return;
        }
        Node<K, V> prev = node.prev;
        Node<K, V> next = node.next;
        if (prev == null && next == null) {
            // size = 1
            // single node
            this.head = null;
            this.tail = null;
        } else if (prev == null) {
            // is head && size > 1
            this.head = next;
            this.head.prev = null;
        } else if (next == null) {
            // is tail && size > 1
            this.tail = prev;
            this.tail.next = null;
        } else {
            // mid && size > 1
            prev.next = next;
            next.prev = prev;
        }
        // 解除引用
        node.prev = null;
        node.next = null;
    }

    @Override
    public Iterator<LRU.Item<K, V>> iterator() {
        Node<K, V> head = this.head;
        return new Iterator<>() {

            private SimpleLRU.Node<K, V> next = head;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public Item<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                SimpleLRU.Node<K, V> current = next;
                this.next = this.next.next;
                return new Item<>(current.key, current.val);
            }
        };
    }

    @RequiredArgsConstructor
    private static class Item<K, V> implements LRU.Item<K, V> {

        private final K key;

        private final V value;

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }
    }

    @Override
    public String toString() {
        return "[" +
                stream().map(item -> "(" + item.key() + "," + item.value() + ")").collect(Collectors.joining(",")) +
                "]";
    }
}
