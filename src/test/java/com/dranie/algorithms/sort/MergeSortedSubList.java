package com.dranie.algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dran
 * @since 2020-08-18
 */
public class MergeSortedSubList {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 5, 7, 9);
        List<Integer> list2 = Arrays.asList(2, 4, 18, 19);
        System.out.println("merged: " + merge(list1, list2));
    }

    private static List<Integer> merge(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList<>(list1.size() + list2.size());
        int i = 0, j = 0;
        while (true) {
            int next;
            if (i < list1.size() && j < list2.size()) {
                int next1 = list1.get(i);
                int next2 = list2.get(j);
                if (next1 < next2) {
                    next = next1;
                    i++;
                } else {
                    next = next2;
                    j++;
                }
            } else if (i < list1.size()) {
                next = list1.get(i);
                i++;
            } else if (j < list1.size()) {
                next = list2.get(j);
                j++;
            } else {
                break;
            }
            list.add(next);
        }
        return list;
    }

    private static List<Integer> mergeUseIterator(List<Integer> list1, List<Integer> list2) {
        List<Integer> list = new ArrayList<>(list1.size() + list2.size());
        return null;
    }
}
