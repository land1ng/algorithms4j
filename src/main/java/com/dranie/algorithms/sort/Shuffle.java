package com.dranie.algorithms.sort;

import com.dranie.algorithms.sort.utils.SortUtil;
import com.dranie.algorithms.utils.Assert;

import java.util.Random;

/**
 * 洗牌算法
 *
 * @author dranfree
 * @version 2019/01/24
 */
public abstract class Shuffle {

    public static void shuffle(int[] origin) {
        shuffle(origin, 0, origin.length - 1);
    }

    public static void shuffle(int[] origin, int lo, int hi) {
        Assert.isTrue(lo >= 0);
        Assert.isTrue(hi <= origin.length - 1);
        Random rnd = new Random();
        for (int i = lo; i <= hi; i++) {
            if (i > 0) {
                int index = rnd.nextInt(i);
                SortUtil.exch(origin, i, index);
            }
        }
    }
}