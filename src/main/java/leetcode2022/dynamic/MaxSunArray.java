package leetcode2022.dynamic;

/**
 * [53]最大子序和：经典动态规划
 *
 * @author Ran.Ding
 * @since 2022/9/7
 */
public class MaxSunArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {  }));
    }

    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length]; // dp[i]为以nums[i]结尾的最大连续子序列的和(num[i]一定会被选取)
        // dp[i]:
        // if dp[i-1]  > 0: dp[i] = dp[i-1] + nums[i]
        // if dp[i-1] <= 0: dp[i] = nums[i]
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
