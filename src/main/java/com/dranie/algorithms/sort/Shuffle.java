package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.SortUtil;

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
                SortUtil.exch(origin, i, index);
            }
        }
    }
}