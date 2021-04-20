package leetcode.dp;

/**
 * @author dingdong
 * @since 2021/4/20
 */
public class UniquePathsWithObstacles {

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{ { 0, 1 }, { 0, 0 } }));
        System.out.println(uniquePathsWithObstacles(new int[][]{ { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } }));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
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
