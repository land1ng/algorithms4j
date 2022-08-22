package leetcode2022;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点  (i,  ai) 。在坐标内画 n 条垂直线，垂直线 i  的两个端点分别为  (i,  ai) 和 (i, 0) 。找出其中的两条线，使得它们与  x  轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * <image src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg"/>
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * <p>
 * 输出：49
 * <p>
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * <p>
 * 双指针指针移动规则：
 * <ol>
 *     <li>向内收窄短板可以获取面积最大值</li>
 *     <li>左边高度小于右边高度的情况下，再怎么收窄右边，面积也不会变大，因为面积取决于矮的一边，因此只能收窄左边。</li>
 * </ol>
 *
 * @author Ran.Ding
 * @since 2022/8/22
 */
public class MaxArea {

    public static void main(String[] args) {

        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static int maxArea(int[] height) {

        int max = 0;

        int len = height.length;

        for (int l = 0, r = len - 1; l < r; ) {
            int area = (r - l) * Math.min(height[l], height[r]);
            if (area > max) {
                max = area;
            }
            // 面积取决于矮的一边，即要获取更大的面积，只能让矮的一边更高。因此需要将矮边向内收。
            if (height[l] < height[r]) {
                // 左边小于右边 -> 左边向前收
                int hl = height[l];
                while (l + 1 < r && height[l + 1] <= hl) l++;
                l++;
            } else {
                // 右边小于(等于)左边 -> 右边向内收
                int hr = height[r];
                while (r - 1 > l && height[r - 1] <= hr) r--;
                r--;
            }
        }

        return max;
    }
}
