package leetcode2022.slidewindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * [3]无重复字符的最长子串：滑动窗口
 *
 * @author RanYeah
 * @since 2022/9/4
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println((int) Character.MAX_VALUE);
        System.out.println(lengthOfLongestSubstring_set("bbbbb"));
        System.out.println(lengthOfLongestSubstring_set("pwwkew"));
        System.out.println(lengthOfLongestSubstring_set("abcabcbb"));
    }

    public static int lengthOfLongestSubstring_set(String s) {
        int ans = 0;
        char[] chs = s.toCharArray();
        // 保存当前窗口中的所有字符
        Set<Character> meet = new HashSet<>();
        for (int l = 0, r = 0; r < chs.length; ) {
            if (meet.contains(chs[r])) {
                // 窗口中包含当前遍历到的字符 -> 左指针向前移，向前移动的过程中移除经过的字符。
                meet.remove(chs[l++]);
            } else {
                meet.add(chs[r++]);
                ans = Math.max(ans, r - l);
            }
        }
        return ans;
    }

    public static int lengthOfLongestSubstring_map(String s) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                // 如果s.charAt(j)在范围[i, j)中，并且假设索引为j'，那么让i直接跳过j'。
                i = Math.max(i, map.get(s.charAt(j)) + 1);
            }
            ans = Math.max(ans, j - i + 1);
            // 0-based index，覆盖下标即可。
            map.put(s.charAt(j), j);
        }
        return ans;
    }

    // 如果字符串只包含 英文字母、数字、符号和空格 的话，可以用这个方法优化性能。
    public static int lengthOfLongestSubstring_array(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();
        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }
}
