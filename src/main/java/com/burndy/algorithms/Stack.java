package com.burndy.algorithms;

import java.util.EmptyStackException;

/**
 * @author ran.ding
 * @since 2021/7/28
 */
public interface Stack<T> {

    T pop() throws EmptyStackException;

    T peek();

    void push(T item);

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
