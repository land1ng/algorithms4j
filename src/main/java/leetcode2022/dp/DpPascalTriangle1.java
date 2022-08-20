package leetcode2022.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpPascalTriangle1 {

    public static void main(String[] args) {
        System.out.println(getRow(1));
    }

    public static List<Integer> getRow(int row) {

        List<Integer> last = Collections.singletonList(1);
        List<Integer> next = last;

        for (int i = 1; i <= row; i++) {
            next = new ArrayList<>(i + 1);
            for (int j = 0; j <= i; j++) {
                int l = get(last, j - 1);
                int r = get(last, j);
                next.add(l + r);
            }
            last = next;
        }

        return next;
    }

    private static int get(List<Integer> row, int index) {
        if (index < 0 || index >= row.size()) {
            return 0;
        } else {
            return row.get(index);
        }
    }
}
