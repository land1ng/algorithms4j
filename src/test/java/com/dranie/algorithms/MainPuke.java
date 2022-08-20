package com.dranie.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class MainPuke {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[] origin = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            index = nextIndex(origin, index);
            origin[index] = in.nextInt();
            if (i != n - 1) {
                index = move(origin, index);
            }
        }
        System.out.println(Arrays.toString(origin));
    }

    private static int nextIndex(int[] a, int i) {
        i = move(a, i);
        return move(a, i);
    }

    private static int move(int[] a, int i) {
        i = (i + 1) < a.length ? (i + 1) : 0;
        while (a[i] > 0) {
            i = (i + 1) < a.length ? (i + 1) : 0;
        }
        return i;
    }
}
