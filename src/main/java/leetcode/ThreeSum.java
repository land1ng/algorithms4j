package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，
 * 判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dingdong
 * @since 2021/4/19
 */
public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{ 1, 2, 3, 4, -1, -2, -3, -1, -2 }));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // 处理边界
        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> tuples = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            for (int l = i + 1, r = nums.length - 1; l < r; ) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    tuples.add(List.of(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return tuples;
    }
}
