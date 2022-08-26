package leetcode2022.bst;

import leetcode.struct.TreeNode;
import leetcode.struct.TreeNodeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树遍历
 *
 * @author RanYeah
 * @since 2022/8/24
 */
public class BstIt {

    public static void main(String[] args) {
        TreeNode root = TreeNodeUtil.build(new Integer[]{ 1, null, 2, 3 });
        System.out.println(traversalInorder(root));
        System.out.println(traversalPreorder(root));
        System.out.println(traversalPostorder(root));
    }

    public static List<Integer> traversalInorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    public static List<Integer> traversalPreorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public static List<Integer> traversalPostorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(root, res);
        return res;
    }

    // 中序遍历
    private static void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    // 前序遍历
    private static void preorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        res.add(node.val);
        preorder(node.left, res);
        preorder(node.right, res);
    }

    // 后序遍历
    private static void postorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        postorder(node.left, res);
        postorder(node.right, res);
        res.add(node.val);
    }
}
