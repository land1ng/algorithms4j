package com.burndy.algorithms.string.trie;

import java.util.List;

/**
 * 单词查找树
 *
 * @param <V> 结点的值
 * @author RanYeah
 * @since 2022/8/28
 */
public interface TrieST<V> {

    /**
     * 查找
     *
     * @param key 键
     * @return 命中的值
     */
    V get(String key);

    /**
     * 构造
     *
     * @param key 键
     * @param val 值
     * @return 旧值
     */
    void add(String key, V val);

    /**
     * 找出符合前缀的所有单词
     *
     * @param pre 前缀
     * @return ~
     */
    List<String> keys(String pre);
}
