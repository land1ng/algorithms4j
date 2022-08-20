package leetcode2022.dp;

/**
 * 斐波那契数列
 *
 * @author RanYeah
 * @see DpClimbStairs
 * @since 2022/8/20
 */
public class DpFib {

    public static void main(String[] args) {
        System.out.println(fib(2));
        System.out.println(fib(3));
    }

    public static int fib(int n) {

        if (n == 0) {
            return 0;
        }

        // f(n) = f(n - 1) + f(n - 2)

        int p = 0;
        int q = 0;
        int r = 1;

        for (int i = 1; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }

        return r;
    }
}
