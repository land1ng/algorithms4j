package leetcode2022.bst;

import leetcode.struct.TreeNode;
import leetcode.struct.TreeNodeUtil;

/**
 * 验证二叉搜索树
 *
 * @author Ran.Ding
 * @since 2022/8/24
 */
public class BstValidate {

    public static void main(String[] args) {
        //      5
        //   4     6
        //       3   7
        TreeNode root = TreeNodeUtil.build(
                new Integer[]{120, 70, 140, 50, 100, 130, 160, 20, 55, 75, 110, 119, 135, 150, 200});
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 验证子树是否有效
     *
     * @param node  根节点
     * @param lower 子树下界，null表示无下界
     * @param upper 子树上界，null表示无上界
     * @return ~
     */
    private static boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        // 左子树必须小于上界
        // 右子树必须大于下界
        return isValidBST(node.left, lower, node.val)
                && isValidBST(node.right, node.val, upper);
    }
}
