package leetcode2022.arr;

import java.util.*;

/**
 * TODO
 *
 * 思路：回溯 剪枝
 * <p>
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * <p>
 * 解集不能包含重复的组合。
 *
 * @author Ran.Ding
 * @since 2022/9/1
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            dfs(candidates, target - candidates[i], i + 1, path, res);
            path.removeLast();
        }
    }
}
