package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author dingdong
 * @since 2021/4/19
 */
public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{ 1, 2, 3, 4, 5 }, 3)));
    }

    public static int[] twoSum(int[] nums, int target) {
        // 处理边界
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        Map<Integer, Integer> indexMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer index = indexMap.get(target - nums[i]);
            if (index != null) {
                return new int[]{ index, i };
            }
            indexMap.put(nums[i], i);
        }
        return new int[0];
    }
}
