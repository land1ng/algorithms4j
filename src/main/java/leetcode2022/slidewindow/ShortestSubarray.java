package leetcode2022.slidewindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * TODO 滑动窗口、前缀和、单调队列
 * <p>
 * [862]和至少为K的最短子数组：滑动窗口、前缀和、单调队列
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 *
 * @author Ran.Ding
 * @since 2022/9/7
 */
public class ShortestSubarray {

    public static void main(String[] args) {

    }

    public static int shortestSubarray(int[] nums, int k) {
        // 前缀和
        // P[i] = nums[0] + nums[1] + ... + nums[i - 1]
        int[] P = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            P[i + 1] = P[i] + nums[i];
        }
        int ans = nums.length + 1;
        // 单调队列
        // 我们用 opt(y) 表示对于固定的 y，最大的满足 P[x] <= P[y] - K 的 x，这样所有 y - opt(y) 中的最小值即为答案。
        Deque<Integer> monoq = new LinkedList<>();
        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()])
                monoq.removeLast();
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + k)
                ans = Math.min(ans, y - monoq.removeFirst());
            monoq.addLast(y);
        }
        return ans < nums.length + 1 ? ans : -1;
    }

}
