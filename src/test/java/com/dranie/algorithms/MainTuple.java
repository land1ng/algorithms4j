package com.dranie.algorithms;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainTuple {

    public static void main(String[] args) {

        System.out.println(threeSum(new int[]{ 3, 3, 6 }));
        System.out.println(threeSum(new int[]{ 3, 6, 3 }));
        System.out.println(threeSum(new int[]{ 6, 3, 3 }));
        System.out.println(threeSum(new int[]{ 4, 2, 2, 2 }));
        System.out.println(threeSum(new int[]{ 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }));

        long start = System.currentTimeMillis();
        int[] a = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1000000))
                .limit(4000)
                .toArray();
        System.out.println(threeSum(a));
        System.out.println("cost: " + (System.currentTimeMillis() - start));
    }

    public static int threeSum(int[] a) {
        // 处理边界
        if (a == null || a.length < 3) {
            return 0;
        }
        int tuples = 0;
        for (int j = 1; j < a.length - 1; j++) {
            for (int i = 0, k = a.length - 1; i < j && k > j; ) {
                int sum = 3 * a[j] - (a[i] + a[k]);
                if (sum == 0) {
                    tuples++;
                    while (i < j - 1 && a[i] == a[i + 1]) {
                        i++;
                        tuples++;
                    }
                    while (k > j + 1 && a[k] == a[k - 1]) {
                        k--;
                        tuples++;
                    }
                }
                if (i < j) {
                    i++;
                } else {
                    k--;
                }
            }
        }
        return tuples;
    }
}
