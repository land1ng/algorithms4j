package leetcode;

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
 * 双指针指针移动规则：向内收窄短板可以获取面积最大值
 *
 * @author dingdong
 * @since 2021/4/19
 */
public class MaxArea {

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{ 4, 3, 2, 1, 4 }));
        System.out.println(maxArea(new int[]{ 1, 8, 6, 2, 5, 4, 8, 3, 7 }));
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        int max = 0;
        for (int l = 0, r = height.length - 1; l < r; ) {
            int area = (r - l) * Math.min(height[l], height[r]);
            if (area > max) {
                max = area;
            }
            // 向内收窄板
            if (height[l] < height[r]) {
                int hl = height[l];
                while (l < r && height[l + 1] <= hl) l++;
                l++;
            } else {
                int hr = height[r];
                while (l < r && height[r - 1] <= hr) r--;
                r--;
            }
        }
        return max;
    }
}
