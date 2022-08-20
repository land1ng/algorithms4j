package leetcode2022.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 杨辉三角
 * <p>
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpPascalTriangle {

    public static void main(String[] args) {
        System.out.println(generate(5));
    }

    public static List<List<Integer>> generate(int rows) {
        if (rows == 0) {
            return Collections.emptyList();
        }
        // dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
        List<List<Integer>> triangle = new ArrayList<>(rows);
        triangle.add(Collections.singletonList(1));
        for (int i = 1; i < rows; i++) {
            List<Integer> last = triangle.get(i - 1);
            List<Integer> next = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                int l = get(last, j - 1);
                int r = get(last, j);
                next.add(l + r);
            }
            triangle.add(next);
        }
        return triangle;
    }

    private static int get(List<Integer> row, int index) {
        if (index < 0 || index >= row.size()) {
            return 0;
        } else {
            return row.get(index);
        }
    }
}
