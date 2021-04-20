package leetcode.dp;

/**
 * 买卖股票的最佳时机
 * <p>
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dingdong
 * @see MaxSubArray
 * @since 2021/4/20
 */
public class MaxProfit {

    public static void main(String[] args) {
        System.out.println(maxProfit(7, 6, 4, 3, 1));
    }

    /**
     * @param prices ~
     * @return ~
     */
    public static int maxProfit(int... prices) {
        int maxAns = 0;
        int minPre = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxAns = Math.max(maxAns, prices[i] - minPre);
            minPre = Math.min(prices[i], minPre);
        }
        return maxAns;
    }
}
