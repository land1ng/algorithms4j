package leetcode2022.arr;

import java.util.*;

/**
 * 思路：回溯 剪枝
 * <p>
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * @author Ran.Ding
 * @since 2022/9/1
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return Collections.emptyList();
        }
        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, path, res);
        return res;
    }

    private static void dfs(int[] candidates, int target, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 小于0的情况已被剪枝
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 每一次搜索的时候设置 下一轮搜索的起点 begin，请看下图。
        // 遇到这一类相同元素不计算顺序的问题，我们在搜索的时候就需要 按某种顺序搜索。
        for (int i = begin; i < candidates.length; i++) {
            // 剪枝，前提是候选数组已经有序。
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            // 下一轮不会再搜索begin以前的数据了，避免重复数据。
            dfs(candidates, target - candidates[i], i, path, res);
            path.removeLast();
        }
    }
}
