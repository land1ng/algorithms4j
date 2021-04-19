package leetcode;

/**
 * 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * @author dingdong
 * @since 2021/4/19
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{ "flower", "flow", "flight" }));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int index = 0;
        String first = strs[0];
        while (index < first.length()) {
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() < index + 1 || strs[i].charAt(index) != first.charAt(index)) {
                    return first.substring(0, index);
                }
            }
            index++;
        }
        return first.substring(0, index);
    }
}
