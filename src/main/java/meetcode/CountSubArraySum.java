package meetcode;

import java.util.Scanner;

/**
 * 最小连续子区间和数量
 *
 * 题目描述
 * 给一串含有c个正整数的数组, 求出有多少个下标的连续区间, 它们的和大于等于x。
 *
 * 解答要求
 * 时间限制：1000ms, 内存限制：100MB
 * 输入
 * 第一行两个整数c x（0 < c <= 1000000, 0 <= x <= 100000000)
 * 第二行有c个正整数（每个正整数小于等于100)。
 *
 * 输出
 * 输出一个整数，表示所求的个数。
 *
 * 样例
 * 输入样例 1 复制
 *
 * 3 6
 * 2 4 7
 * 输出样例 1
 *
 * 4
 * 提示样例 1
 *
 * @author ran
 * @since 2022/7/5
 */
public class CountSubArraySum {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        int x = in.nextInt();
        int[] nums = new int[c];
        for (int i = 0; i < c; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(count(nums, x));
    }

    private static int count(int[] nums, int x) {
        int res = 0;
        int sum = 0;
        int c = nums.length;
        boolean moved = true; // 右指针是否移动了
        for (int i = 0, j = 0; i < c && j < c; ) {
            if (moved) {
                sum += nums[j];
            }
            if (sum >= x) {
                res += c - j;
                // 缩小窗口
                // 左指针向右移
                sum -= nums[i++];
                moved = false;
            } else {
                j++;
                moved = true;
            }
        }
        return res;
    }
}
