package leetcode2022.dp;

import java.util.Arrays;

/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpCountBits {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits(5)));
        System.out.println(Arrays.toString(dpCountBits(5)));
    }

    public static int[] countBits(int n) {

        int[] count = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            count[i] = countBit(i);
        }

        return count;
    }

    private static int countBit(int x) {
        int count = 0;
        while (x > 0) {
            // 将 x 的二进制表示的最后一个 1 变成 0
            x &= x - 1;
            count++;
        }
        return count;
    }

    // 动态规划方案
    // x = 1000 0101 0010 1000
    // y = 1000 0000 0000 0000
    // z = x - y  =
    //     0000 0101 0010 1000
    //           <= x
    // bits(x) = bits(z) + 1
    public static int[] dpCountBits(int n) {

        int[] bits = new int[n + 1];

        int highBit = 0;

        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }
}
