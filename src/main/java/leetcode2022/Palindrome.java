package leetcode2022;

/**
 * 验证是否为回文串
 * <p>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * @author RanYeah
 * @since 2022/8/23
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("race a car"));
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String s) {
        char[] chs = s.toCharArray();
        for (int l = 0, r = chs.length - 1; l < r; ) {
            while (l < r && !Character.isLetterOrDigit(chs[l])) l++;
            while (l < r && !Character.isLetterOrDigit(chs[r])) r--;
            if (Character.toLowerCase(chs[l]) == Character.toLowerCase(chs[r])) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
