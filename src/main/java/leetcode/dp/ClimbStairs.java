package leetcode.dp;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例：
 * <p>
 * 输入：2
 * <p>
 * 输出：2
 * <p>
 * 解释：有两种方法可以爬到楼顶。
 * <ol>
 *     <li>1 阶 + 1 阶</li>
 *     <li>2 阶</li>
 * </ol>
 *
 * @author dingdong
 * @since 2021/4/21
 */
public class ClimbStairs {

    public static void main(String[] args) {
        System.out.println(climbStairs0(2));
        System.out.println(climbStairs0(3));
        System.out.println(climbStairs0(4));
    }

    // 滚动数组优化空间复杂度
    public static int climbStairs(int n) {
        int p = 0;
        int q = 0;
        int r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // f(i) = f(i - 1) + f(i - 2)
    // 因为每次只能爬 1 级或 2 级，所以 f(x) 只能从 f(x−1) 和 f(x−2) 转移过来。
    public static int climbStairs0(int n) {
        if (n == 1) {
            return 1;
        }
        // dp[i]保存爬到第i级阶梯有多少种方法
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
