package leetcode2022.dynamic;

/**
 * 最长回文子串
 *
 * @author RanYeah
 * @since 2022/8/20
 */
public class DpLongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }

    public static String longestPalindrome(String s) {

        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int maxBegin = 0;
        // dp[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化所有长度为1的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] chs = s.toCharArray();
        // 枚举子串长度
        for (int l = 2; l <= len; l++) {
            // 左边界
            for (int i = 0; i < len; i++) {
                // 右边界
                int j = i + l - 1;
                if (j >= len) {
                    break;
                }
                if (l == 2) {
                    dp[i][j] = chs[i] == chs[j];
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && (chs[i] == chs[j]);
                }
                if (dp[i][j] && (j - i + 1) > maxLen) {
                    maxLen = j - i + 1;
                    maxBegin = i;
                }
            }
        }
        return s.substring(maxBegin, maxBegin + maxLen);
    }
}
