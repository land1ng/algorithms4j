package com.dranie.algorithms;

import java.util.EmptyStackException;

/**
 * 下压栈
 *
 * @author dranfree
 * @since 2020.05.31
 */
public class LinkedStack<T> implements Stack<T> {

    private Node<T> top;

    private int size;

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void push(T element) {
        if (top == null)
            top = new Node<>(element, null);
        else
            top = new Node<>(element, top);
        size++;
    }

    @Override
    public T pop() {
        if (top == null)
            throw new EmptyStackException();
        Node<T> node = top;
        top = top.next;
        size--;
        return node.value;
    }

    @Override
    public T peek() {
        if (top == null)
            return null;
        Node<T> node = top;
        top = top.next;
        size--;
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}