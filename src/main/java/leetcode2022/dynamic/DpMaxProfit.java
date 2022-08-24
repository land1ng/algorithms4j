package leetcode2022.dynamic;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpMaxProfit {

    public static void main(String[] args) {

        // 动态规划解法：
        // dp[i] = max(dp[i−1], prices[i] − minprice)

        System.out.println(maxProfit(new int[]{ 7, 6, 4, 3, 1 }));
    }

    public static int maxProfit(int[] prices) {

        int res = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                res = Math.max(res, prices[i] - min);
            }
        }

        return res;
    }
}
