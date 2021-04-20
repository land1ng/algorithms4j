package leetcode.dp;

/**
 * 最小路径和(动态规划)
 * <p>
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * <image src="https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg" />
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * <p>
 * 输出：7
 * <p>
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 算法思路：
 * <p>
 * 状态转移方程：
 *
 * @author dingdong
 * @since 2021/4/20
 */
public class MinPathSum {

    /**
     * 状态转移方程：
     * <p>
     * 当 i > 0 且 j = 0 时，dp[i][0] = dp[i-1][0] + grid[i][0] = dp[i−1][0] + grid[i][0]。
     * <p>
     * 当 i = 0 且 j > 0 时，dp[0][j] = dp[0][j-1] + grid[0][j] = dp[0][j−1] + grid[0][j]。
     * <p>
     * 当 i > 0 且 j > 0 时，dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]。
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        // 处理边界
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        // DP数组
        // dp[i][j] 表示从 (0,0) 出发到 (i,j) 位置的最小路径和
        int[][] dp = new int[rows][cols];
        dp[0][0] = grid[0][0];
        // 处理两条边
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < cols; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // 处理中间的点
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
