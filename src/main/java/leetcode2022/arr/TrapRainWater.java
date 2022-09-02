package leetcode2022.arr;

/**
 * 接雨水
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * max(n_l) = max(max(n-1_l), height[n-1])
 * max(n_r) = {
 * if (height[n-1] == max(n-1_r)) -> 0
 * }
 *
 * @author Ran.Ding
 * @since 2022/9/2
 */
public class TrapRainWater {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int trap(int[] height) {
        // 一次遍历获取每个i位置左边和右边的最大高度(包括自身)
        int[] maxL = new int[height.length];
        int[] maxR = new int[height.length];
        maxL[0] = height[0];
        maxR[height.length - 1] = height[height.length - 1];
        // 正反向同时遍历
        for (int l = 1; l < height.length; l++) {
            int r = height.length - l - 1;
            maxL[l] = Math.max(height[l], maxL[l - 1]);
            maxR[r] = Math.max(height[r], maxR[r + 1]);
        }
        // 再遍历一次，计算雨水总和。
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            ans += Math.max(0, Math.min(maxL[i], maxR[i]) - height[i]);
        }
        return ans;
    }

    // 朴素解法：计算每个i下标所能达到的最大高度，每个下标都要想两边扫描，获取最大值。
    public static int trap_simple(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        // 朴素解法
        int res = 0;
        for (int c = 1; c < height.length - 1; c++) {
            // 中心扩散
            int maxL = c - 1;
            int maxR = c + 1;
            for (int l = c - 1, r = c + 1; l >= 0 || r < height.length; l--, r++) {
                if (l >= 0 && height[l] > height[maxL]) {
                    maxL = l;
                }
                if (r < height.length && height[r] > height[maxR]) {
                    maxR = r;
                }
            }
            res += Math.max(0, Math.min(height[maxL], height[maxR]) - height[c]);
        }
        return res;
    }
}
