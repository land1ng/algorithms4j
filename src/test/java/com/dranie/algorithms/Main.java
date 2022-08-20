package com.dranie.algorithms;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();

        int[] deadlines = new int[n];

        for (int i = 0; i < n; i++) {
            deadlines[i] = in.nextInt();
        }

        Arrays.sort(deadlines);

        int times = 1;

        // 可以准时送达的单数
        int countInTime = 0;

        for (int deadline : deadlines) {
            if (deadline >= times * t) {
                times++;
                countInTime++;
            }
        }

        System.out.println(n - countInTime);
    }
}
