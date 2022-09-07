package com.burndy.algorithms.classic;

/**
 * 快速幂算法
 *
 * @author Ran.Ding
 * @since 2022/9/7
 */
public class FastPower {

    public static void main(String[] args) {
        System.out.println(bpow(5, 2));
        System.out.println(bpow(5, 10));
    }

    // 快速幂
    public static long bpow(long x, long n) {
        if (n == 0) return 1;
        long res = bpow(x, n / 2);
        if ((n % 2) > 0) {
            return res * res * x;
        } else {
            return res * res;
        }
    }

    // 计算 (x^n)%m
    public static int pmod(int x, int n, int m) {
        x %= m;
        int res = 1;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = res * x % m;
            }
            x = x * x % m;
            n >>= 1;
        }
        return res;
    }
}
