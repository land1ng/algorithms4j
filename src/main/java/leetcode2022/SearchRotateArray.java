package leetcode2022;

/**
 * 搜索旋转排序数组
 *
 * @author Ran.Ding
 * @since 2022/8/23
 */
public class SearchRotateArray {

    public static void main(String[] args) {

    }

    // 标准答案，真漂亮！
    public static int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                // [0, mid] 范围内有序
                if (nums[0] <= target && target < nums[mid]) {
                    // [0, mid] 有序，并且 target ∈ [nums[0],nums[mid])，那么去 [0, mid - 1] 范围内查。
                    r = mid - 1;
                } else {
                    // [0, mid] 有序，并且 target 不在有序的数组范围内，那么去 [mid + 1, r] 范围内查。
                    l = mid + 1;
                }
            } else {
                // [0, mid] 不完全有序，那么 [mid + 1, n - 1] 内一定是有序的。
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int search1(int[] nums, int target) {
        int len = nums.length;
        // 先找出旋转中心
        int c = findRotatePoint(nums);
        // c     是原数组的末尾
        // c + 1 是原数组的开始
        int m;
        int l = 0, r = len - 1;
        do {
            m = (r - l) / 2 + l;
            int rotate = rotate(m, c, len);
            if (nums[rotate] == target) {
                return rotate;
            }
            if (nums[rotate] > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        } while (l <= r);
        return -1;
    }

    private static int rotate(int i, int c, int len) {
        int ii = i + c + 1;
        return ii >= len ? (ii - len) : ii;
    }

    // min(left) > max(right)
    private static int findRotatePoint(int[] nums) {
        if (nums.length == 1) {
            return -1;
        }
        int c = -1;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            if (nums[l] < nums[r]) {
                break;
            }
            c = (r - l) / 2 + l;
            if (nums[c] > nums[c + 1]) {
                break;
            }
            if (nums[c] < nums[0]) {
                r = c;
            } else {
                l = c;
            }
        }
        return c;
    }
}
