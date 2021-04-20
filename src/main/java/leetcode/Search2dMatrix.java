package leetcode;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * <image src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg"/>
 *
 * @author dingdong
 * @since 2021/4/20
 */
public class Search2dMatrix {

    public static void main(String[] args) {
        System.out.println(searchMatrix(new int[][]{ { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } }, 13));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        // 处理边界
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int total = matrix.length * matrix[0].length;
        // 二分法
        return search(matrix, target, 0, total);
    }

    // [lo, hi)
    private static boolean search(int[][] matrix, int target, int lo, int hi) {
        if (lo >= hi) {
            return false;
        }
        int mi = (hi - lo) / 2 + lo;
        // to 2d index
        int mix = mi / matrix[0].length;
        int miy = mi % matrix[0].length;

        if (matrix[mix][miy] == target) {
            return true;
        } else if (matrix[mix][miy] > target) {
            return search(matrix, target, lo, mi);
        } else if (matrix[mix][miy] < target) {
            return search(matrix, target, mi + 1, hi);
        }

        return false;
    }

}
