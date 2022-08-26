package leetcode2022.bst;

import leetcode.struct.TreeNode;
import leetcode.struct.TreeNodeUtil;

import java.util.List;

/**
 * 合并二叉树
 *
 * @author Ran.Ding
 * @since 2022/8/26
 */
public class BstMerge {

    public static void main(String[] args) {
        TreeNode root1 = TreeNodeUtil.build(new Integer[]{1, 3, 2, 5});
        TreeNode root2 = TreeNodeUtil.build(new Integer[]{2, 1, 3, null, 4, null, 7});
        // 前序遍历过程中合并
        TreeNode newRoot = mergeTrees(root1, root2);
        List<Integer> preorder = BstIt.traversalPreorder(newRoot);
        System.out.println(preorder);
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null ? root2 : root1;
        }
        TreeNode newRoot = new TreeNode(root1.val + root2.val);
        newRoot.left = mergeTrees(root1.left, root2.left);
        newRoot.right = mergeTrees(root1.right, root2.right);
        return newRoot;
    }
}
