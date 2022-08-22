package leetcode2022.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpPascalTriangleGetRow {

    public static void main(String[] args) {
        System.out.println(getRow(0));
        System.out.println(getRow(1));
        System.out.println(getRow(2));
        System.out.println(getRow(3));
        System.out.println(getRow(4));
    }

    public static List<Integer> getRow(int row) {

//        List<Integer> last = Collections.singletonList(1);
//        List<Integer> next = last;
//        for (int i = 1; i <= row; i++) {
//            next = new ArrayList<>(i + 1);
//            for (int j = 0; j <= i; j++) {
//                int l = get(last, j - 1);
//                int r = get(last, j);
//                next.add(l + r);
//            }
//            last = next;
//        }
//        return next;

        // 滚动数组优化
        List<Integer> res = new ArrayList<>();
        res.add(1);
        // 倒着计算当前行
        for (int i = 1; i <= row; i++) {
            res.add(0);
            for (int j = i; j > 0; j--) {
                //  1 3 3 1 0
                // 1 4 6 4 1
                // 当计算到第j项时，第j-1项还是上一行的值。
                res.set(j, res.get(j) + res.get(j - 1));
            }
        }
        return res;
    }

    private static int get(List<Integer> row, int index) {
        if (index < 0 || index >= row.size()) {
            return 0;
        } else {
            return row.get(index);
        }
    }
}
