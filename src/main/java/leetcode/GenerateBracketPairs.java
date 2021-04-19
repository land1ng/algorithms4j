package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (()())
 * <p>
 * LeetCode:
 * https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 *
 * @author dingdong
 * @since 2021/4/16
 */
public class GenerateBracketPairs {

    private static final char LEFT = '(';

    private static final char RIGHT = ')';

    public static void main(String[] args) {
        System.out.println(create(2));
    }

    public static List<String> create(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        dfs("", n, n, result);
        return result;
    }

    private static void dfs(String target, int left, int right, List<String> result) {
        // 终止
        if (left == 0 && right == 0) {
            result.add(target);
            return;
        }
        // 剪枝
        if (left > right) {
            return;
        }
        if (left > 0) {
            dfs(target + LEFT, left - 1, right, result);
        }
        if (right > 0) {
            dfs(target + RIGHT, left, right - 1, result);
        }
    }
}
