package leetcode2022.slidewindow;

import java.util.HashSet;
import java.util.Set;

/**
 * [219]存在重复元素：滑动窗口
 *
 * @author RanYeah
 * @since 2022/9/5
 */
public class ContainsNearbyDuplicate {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                window.remove(nums[i - k - 1]);
            }
            // 添加失败 -> 存在重复元素
            if (!window.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
