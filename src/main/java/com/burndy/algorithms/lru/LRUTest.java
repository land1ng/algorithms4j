package com.burndy.algorithms.lru;

/**
 * @author ran.ding
 * @since 2021/9/27
 */
public class LRUTest {

    public static void main(String[] args) {
        LRU<Integer, Integer> lru = new SimpleLRU<>(5);
        lru.get(1, 1);
        lru.get(2, 1);
        lru.get(3, 1);
        lru.get(4, 1);
        lru.get(3, 1);
        lru.get(4, 1);
        lru.get(5, 1);
        lru.get(6, 1);
        lru.get(11, 1);
        lru.get(12, 1);
        lru.get(14, 1);
        lru.remove(-1);
        lru.remove(1);
        System.out.println(lru);
    }
}
