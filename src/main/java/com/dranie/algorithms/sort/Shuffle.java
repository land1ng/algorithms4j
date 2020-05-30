package com.dranie.algorithms.sort;

import java.util.Random;

/**
 * 洗牌算法
 *
 * @author dranfree
 * @version 2019/01/24
 */
public abstract class Shuffle {
    public static void shuffle(int[] origin) {
        Random rnd = new Random();
        for (int i = 0; i < origin.length; i++) {
            if (i > 0) {
                int index = rnd.nextInt(i);
                swap(origin, i, index);
            }
        }
    }
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}