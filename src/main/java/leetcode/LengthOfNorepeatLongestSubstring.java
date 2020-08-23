package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 滑动窗口
 * @author dranfree
 * @since 2019.05.04
 */
public class LengthOfNorepeatLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                // 从左往右依次移除元素，直到j所在元素与窗口中的元素不重复为止。
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring0(String s) {
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                // 如果s.charAt(j)在范围[i, j)中，并且假设索引为j'，那么让i直接跳过j'。
                i = Math.max(i, map.get(s.charAt(j)) + 1);
            }
            ans = Math.max(ans, j - i + 1);
            // 0-based index
            map.put(s.charAt(j), j);
        }
        return ans;
    }

    public static void main(String[] args) {
//        int max = new LengthOfNorepeatLongestSubstring().lengthOfLongestSubstring0("abbaabcdaab");
//        System.out.println(max);
        System.out.println(12 & 23);
    }
}