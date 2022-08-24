package com.burndy.algorithms.bst;

import java.util.Iterator;
import java.util.Optional;

/**
 * Standard B-Tree 标准B树实现（多向平衡树）
 * <ul>
 *     <li>B-树的成本模型：我们使用<b>页的访问次数</b>（无论读写）作为外部查找算法的成本模型</li>
 *     <li>不将数据保存在树中，而是会构造一棵由键的副本组成的树，每个副本都关联着一条链接。</li>
 *     <li>选择一个参数 M（一般都是一个偶数）并构造一棵多向树，
 *     每个结点最多含有 M-1 对键和链接（假设 M 足够小，
 *     使得每个 M 向结点都能够存放在一个页中），
 *     最少含有 M/2 对键和链接（以提供足够多的分支来保证查找路径较短）。</li>
 * </ul>
 *
 * @author ran.ding
 * @since 2021/7/30
 */
public interface MultiwayBST<K extends Comparable<K>, V> {

    Optional<V> get(K key);

    Optional<V> put(K key, V val);

    Optional<V> delete(K key);

    interface Page<K> {

        void close();

        void add(K key);

        void add(Page<K> subPage);

        /**
         * 页是否已经溢出
         *
         * @return ~
         */
        boolean isFull();

        /**
         * 这是一个外部页吗
         *
         * @return ~
         */
        boolean isExternal();

        /**
         * 该key包含在页中吗
         *
         * @param key 键
         * @return ~
         */
        boolean contains(K key);

        /**
         * 页分裂
         * <p>
         * 在分裂一张饱和页时，
         * split() 方法会将排序后位置正好大于（或等于）M/2 的键移动到一个新的 Page 对象中，并返回
         * 该对象的引用。
         *
         * @return ~
         */
        Page<K> split();

        /**
         * 可能含有键 key 的子树
         *
         * @param key 键
         * @return ~
         */
        Page<K> next(K key);

        /**
         * 页中所有键的迭代器
         *
         * @return ~
         */
        Iterator<K> keys();
    }
}
