package leetcode2022.dynamic;

/**
 * 746. 使用最小花费爬楼梯
 * <p>
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * <p>
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * <p>
 * 请你计算并返回达到楼梯顶部的最低花费。
 * <p>
 * 来源：力扣（LeetCode）
 * <p>
 * 链接：<a href="https://leetcode.cn/problems/min-cost-climbing-stairs">使用最小花费爬楼梯</a>
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpMinCostClimbingStairs {

    public static int minCostClimbingStairs(int[] cost) {

        if (cost.length == 1) {
            return cost[0];
        }

        int i, total;
        if (cost[0] < cost[1]) {
            i = 0;
            total = cost[0];
        } else {
            i = 1;
            total = cost[1];
        }

        // TODO
        return 1;
    }
}
