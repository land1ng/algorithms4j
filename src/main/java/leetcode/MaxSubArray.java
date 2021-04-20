package leetcode;

/**
 * 动态规划-简单
 * <p>
 * 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * 算法思路：
 * <p>
 * 我们用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
 * <p>
 * max{f(i)}, 0<=i<=n-1
 * <p>
 * 因此我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。
 * <p>
 * 我们可以考虑 nums[i] 单独成为一段还是加入 f(i-1) 对应的那一段，这取决于 nums[i] 和 f(i-1)+nums[i] 的大小，
 * 我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
 * <p>
 * f(i) = max{f(i-1) + nums[i], nums[i]}
 *
 * @author dingdong
 * @since 2021/4/20
 */
public class MaxSubArray {

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{ -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }

    public static int maxSubArray(int[] nums) {
        // 表示f(i)中最大子序列和
        int pre = 0;
        // 标志f(i)集合中的最大值
        int max = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            max = Math.max(max, pre);
        }
        return max;
    }
}
