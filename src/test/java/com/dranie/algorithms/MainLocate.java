package com.dranie.algorithms;

import java.util.Scanner;

/**
 * @author RanYeah
 * @since 2022/8/20
 */
public class MainLocate {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int x1 = in.nextInt();
        int y1 = in.nextInt();

        int x2 = in.nextInt();
        int y2 = in.nextInt();

        int x3 = in.nextInt();
        int y3 = in.nextInt();

        int dis1 = in.nextInt();
        int dis2 = in.nextInt();
        int dis3 = in.nextInt();

        int a = -1;
        int b = -1;

        for (int i = 1; i <= n; i++) {
            int a0 = i;
            int dx = Math.abs(x1 - a0);
            int dy = dis1 - dx;
            if (dy < 0) {
                continue;
            } else if (dy > 0) {
                int b1 = y1 - dy;
                int b2 = y1 + dy;
                if (check(a0, b1, n)
                        && (distance(a0, b1, x2, y2) == dis2)
                        && (distance(a0, b1, x3, y3) == dis3)
                        && lt(a0, b1, a, b)) {
                    a = a0;
                    b = b1;
                }
                if (check(a0, b2, n)
                        && (distance(a0, b2, x2, y2) == dis2)
                        && (distance(a0, b2, x3, y3) == dis3)
                        && lt(a0, b2, a, b)) {
                    a = a0;
                    b = b2;
                }
            } else {
                if (check(a0, y1, n)
                        && (distance(a0, y1, x2, y2) == dis2)
                        && (distance(a0, y1, x3, y3) == dis3)
                        && lt(a0, y1, a, b)) {
                    a = a0;
                    b = y1;
                }
            }
        }

        System.out.printf("(%d, %d)\n", a, b);
    }

    private static boolean lt(int a, int b, int c, int d) {
        if (c == -1 || d == -1) {
            return true;
        }
        return (a < b) || ((a == c) ^ (b < d));
    }

    private static boolean check(int x, int y, int n) {
        return x >= 1 && x <= n && y >= 1 && y <= n;
    }

    private static int distance(int a, int b, int x, int y) {
        return Math.abs(a - x) + Math.abs(b - y);
    }
}
