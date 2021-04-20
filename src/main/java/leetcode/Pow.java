package leetcode;

/**
 * 快速幂算法
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 *
 * @author dingdong
 * @since 2021/4/20
 */
public class Pow {

    public static void main(String[] args) {
        System.out.println(myPow(1, Integer.MIN_VALUE));
    }

    public static double myPow(double x, int n) {
        return n >= 0 ? quickPow(x, n) : 1.0 / quickPow(x, -n);
    }

    private static double quickPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double y = quickPow(x, n / 2);
        return n % 2 == 0 ? y * y : y * y * x;
    }
}
