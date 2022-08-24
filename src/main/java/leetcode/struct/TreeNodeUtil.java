package leetcode.struct;

import lombok.experimental.UtilityClass;

/**
 * 二叉树工具类
 *
 * @author Ran.Ding
 * @since 2022/8/24
 */
@UtilityClass
public class TreeNodeUtil {

    /**
     * 按照给定的数组构建二叉树(是否有效依赖于数据正确性)
     *
     * @param tree ~
     * @return 二叉树的根节点
     */
    public static TreeNode build(Integer[] tree) {
        // left:  2n + 1
        // right: 2n + 2
        return build(tree, 0);
    }

    private static TreeNode build(Integer[] tree, int index) {
        // 剪枝
        if (index >= tree.length || tree[index] == null) {
            return null;
        }
        TreeNode root = new TreeNode(tree[index]);
        root.left = build(tree, 2 * index + 1);
        root.right = build(tree, 2 * index + 2);
        return root;
    }
}
