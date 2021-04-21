package leetcode.dp;

/**
 * @author dingdong
 * @since 2021/4/20
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles0(new int[][]{ { 0, 1 }, { 0, 0 } }));
        System.out.println(uniquePathsWithObstacles0(new int[][]{ { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }));
    }

    // 可以将 O(mn) 优化为 O(n)
    // 滚动数组思想优化空间复杂度
    // 当我们定义的状态在动态规划的转移方程中只和某几个状态相关的时候，就可以考虑这种优化方法，目的是给空间复杂度「降维」。
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] f = new int[n];
        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        // 一行一行的来计算
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < n; ++j) {
                if (row[j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && row[j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
            // 按列循环完成之后，f[j]表示可以到达本行末尾的路径计数。
        }
        return f[n - 1];
    }

    public static int uniquePathsWithObstacles0(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        if (hasObstacle(obstacleGrid, 0, 0)) {
            return 0;
        } else {
            dp[0][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            if (hasObstacle(obstacleGrid, i, 0)) {
                dp[i][0] = 0;
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            if (hasObstacle(obstacleGrid, 0, i)) {
                dp[0][i] = 0;
                break;
            } else {
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (hasObstacle(obstacleGrid, i, j)) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    private static boolean hasObstacle(int[][] grid, int m, int n) {
        return grid[m][n] == 1;
    }
}
