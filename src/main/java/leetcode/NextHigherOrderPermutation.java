package leetcode;

import java.util.Arrays;

/**
 * 下一个排列算法
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 输入：nums = [4,3,2,1]
 * 输出：[1,2,3,4]
 * <p>
 * 标准算法：
 * 1.从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
 * 2.在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
 * 3.将 A[i] 与 A[k] 交换
 * 4.可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
 * 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 * <p>
 * 作者：imageslr
 * 链接：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 该方法支持数据重复，且在 C++ STL 中被采用。
 *
 * @author dingdong
 * @since 2021/4/19
 */
public class NextHigherOrderPermutation {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            // 从后向前查找第一个相邻升序的元素对 (i-1,i)，满足 A[i-1] < A[i]。
            if (nums[i - 1] < nums[i]) {
                // [i,end)为降序
                // [i,end)从后向前查找第一个满足 A[i-1] < A[k] 的 k
                for (int k = nums.length - 1; k >= i; k--) {
                    if (nums[k] > nums[i - 1]) {
                        swap(nums, k, i - 1);
                        // 此时，[i,end)为降序，将其变为升序。
                        reverse(nums, i, nums.length - 1);
                        break;
                    }
                }
                return;
            }
        }
        // 到最后都没找到符合条件的元素对
        reverse(nums, 0, nums.length - 1);
    }

    private static void reverse(int[] a, int lo, int hi) {
        // reverse
        for (int l = lo, r = hi; l < r; l++, r--) {
            swap(a, l, r);
        }
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) return;
        a[i] ^= a[j];
        a[j] ^= a[i];
        a[i] ^= a[j];
    }
}
