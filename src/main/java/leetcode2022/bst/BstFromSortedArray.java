package leetcode2022.bst;

import com.burndy.algorithms.utils.Assert;
import leetcode.struct.TreeNode;
import leetcode.struct.TreeNodeUtil;

/**
 * 将有序数组转换为二叉搜索树
 * <p>
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *
 * @author Ran.Ding
 * @since 2022/8/24
 */
public class BstFromSortedArray {

    public static void main(String[] args) {
        TreeNode root = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        Assert.isTrue(TreeNodeUtil.validate(root));
        System.out.println(root);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return buildRoot(nums, 0, nums.length - 1);
    }

    // l: inclusive
    // r: exclusive
    private static TreeNode buildRoot(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (r - l) / 2 + l;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildRoot(nums, l, mid - 1);
        root.right = buildRoot(nums, mid + 1, r);
        return root;
    }
}
