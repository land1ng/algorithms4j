package com.burndy.algorithms.sort;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Random;

/**
 * @author dran
 * @since 2020-08-18
 */
public class MergeSortedSubList1 {

    private static final Random rd = new Random();

    @AllArgsConstructor
    private static class ListNode<T> {
        private final T item;
        private ListNode<T> next;
    }

    public static void main(String[] args) {
        ListNode<Integer> list1 = new ListNode<>(1, null);
        ListNode<Integer> list2 = new ListNode<>(2, null);
        randomList(list1, 10);
        randomList(list2, 20);
        ListNode<Integer> retFirst = merge(list1, list2);
        ListNode<Integer> current = retFirst;
        while (current != null) {
            System.out.println(current.item);
            current = current.next;
        }
    }

    private static void randomList(ListNode<Integer> first, int length) {
        ListNode<Integer> current = first;
        for (int i = 0; i < length; i++) {
            current.next = new ListNode<>(current.item + rd.nextInt(10), null);
            current = current.next;
        }
    }

    private static ListNode<Integer> merge(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> retList = null;
        ListNode<Integer> currentRet = null;
        ListNode<Integer> current1 = list1;
        ListNode<Integer> current2 = list2;
        while (true) {
            int next;
            if (current1 != null && current2 != null) {
                if (current1.item < current2.item) {
                    next = current1.item;
                    current1 = current1.next;
                } else {
                    next = current2.item;
                    current2 = current2.next;
                }
            } else if (current1 != null) {
                next = current1.item;
                current1 = current1.next;
            } else if (current2 != null) {
                next = current2.item;
                current2 = current2.next;
            } else {
                break;
            }
            if (retList == null) {
                currentRet = retList = new ListNode<>(next, null);
            } else {
                currentRet.next = new ListNode<>(next, null);
                currentRet = currentRet.next;
            }
        }
        return retList;
    }
}
