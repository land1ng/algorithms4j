package leetcode2022;

/**
 * 有效的数独
 * <p>
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 注意：
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示。
 *
 * @author Ran.Ding
 * @since 2022/8/23
 */
public class SudokuValidate {

    public static void main(String[] args) {
        char[][] board = {
                { '5','3','.','.','7','.','.','.','.' },
                { '6','.','.','1','9','5','.','.','.' },
                { '.','9','8','.','.','.','.','6','.' },
                { '8','.','.','.','6','.','.','.','3' },
                { '4','.','.','8','.','3','.','.','1' },
                { '7','.','.','.','2','.','.','.','6' },
                { '.','6','.','.','.','.','2','8','.' },
                { '.','.','.','4','1','9','.','.','5' },
                { '.','.','.','.','8','.','.','7','9' }
        };
        System.out.println(isValidSudoku(board));
    }

    public static boolean isValidSudoku(char[][] board) {
        // 一行一个数组
        // 一列一个数组
        // 一个九宫格一个数组
        int[][] rows = new int[9][];
        int[][] cols = new int[9][];
        int[][] grid = new int[9][];
        // 一次遍历，空间换时间。
        for (int r = 0; r < board.length; r++) {
            int[] row = getRow(rows, r);
            for (int c = 0; c < board[r].length; c++) {
                int n = toInt(board[r][c]);
                if (n != 0 && row[toInt(board[r][c])]++ > 0) {
                    return false;
                }
                if (n != 0 && getCol(cols, c)[n]++ > 0) {
                    return false;
                }
                if (n != 0 && getGrid(grid, r, c)[n]++ > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] getRow(int[][] rows, int r) {
        if (rows[r] == null) {
            rows[r] = new int[10];
        }
        rows[r][0] = 0;
        return rows[r];
    }

    private static int[] getCol(int[][] cols, int c) {
        if (cols[c] == null) {
            cols[c] = new int[10];
        }
        cols[c][0] = 0;
        return cols[c];
    }

    private static int[] getGrid(int[][] grid, int r, int c) {
        int gr = (int) Math.ceil((r + 1) / 3.0);
        int gc = (int) Math.ceil((c + 1) / 3.0);
        int index = (gr - 1) * 3 + gc - 1;
        if (grid[index] == null) {
            grid[index] = new int[10];
        }
        grid[index][0] = 0;
        return grid[index];
    }

    private static int toInt(char ch) {
        if ('.' == ch) {
            return 0;
        } else {
            return (ch - '0');
        }
    }
}
