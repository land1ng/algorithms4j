package leetcode2022.dp;

/**
 * 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 其实就是斐波那契数列
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpClimbStairs {

    public int climbStairs(int n) {

//        int[] dp = new int[n];
//
//        // dp[i] 表示爬到第 i+1 阶阶梯的方法
//        dp[0] = 1;
//        dp[1] = 2;
//
//        // dp[n] = dp[n-1] + dp[n-2]
//        for (int i = 2; i < n; i++) {
//            dp[i] = dp[i - 1] + dp[i - 2];
//        }
//
//        return dp[n - 1];

        // 滚动数组

        // dp[i - 2]
        int p = 0;
        // dp[i - 1]
        int q = 0;
        // dp[i]
        int r = 1;

        for (int i = 1; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }

        return r;
    }
}
