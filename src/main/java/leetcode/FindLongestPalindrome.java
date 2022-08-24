package leetcode;

import com.burndy.algorithms.utils.Optimizable;

import java.util.Random;

/**
 * 寻找最长回文子串
 *
 * 滑动窗口？
 *
 * TODO
 *
 * @author dranfree
 * @since 2019.05.17
 */
@Optimizable
public class FindLongestPalindrome {

    /**
     * 中心扩展算法
     * @param s
     * @return
     */
    static String findPalindrom(String s) {
        if (s == null || s.length() == 0)
            return "";
        int start = 0, end = 0;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int len1 = expandAroundCenter(chs, i, i);     // len1奇数
            int len2 = expandAroundCenter(chs, i, i + 1); // len2偶数
            int len = Math.max(len1, len2);
            if (len > end  + 1 - start) {
                start = i - (len - 1) / 2;
                end   = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    static int expandAroundCenter(char[] chs, int l0, int r0) {
        int l = l0, r = r0;
        while (true) {
            if (l >= 0 && r < chs.length && chs[l] == chs[r]) {
                l--;
                r++;
                continue;
            }
            break;
        }
        return (r - 1) - (l + 1) + 1;
    }


    /**
     * 时间复杂度为o(n)的马拉车算法！
     * @param origin
     * @return
     */
    static String findWithManacher(String origin) {
        return null;
    }


    /**
     * 自己写的垃圾……比较接近中心扩展算法
     * @param s
     * @return
     */
    static String find(String s) {
        if (s == null || s.length() == 0)
            return "";
        char[] chs = s.toCharArray();
        String longest = null;
        int minl = 0, maxr = 1;
        for (int i = 0; i < chs.length; i++) {
            int l = i;
            int r = i + 1;
            // 确定中心
            char mid = chs[l];
            while (true) {
                if (l - 1 >= 0 && chs[l - 1] == mid)
                    l--;
                else break;
            }
            while (true) {
                if (r < chs.length && chs[r] == mid)
                    r++;
                else break;
            }
            while (true) {
                if (l - 1 >= 0 && r < chs.length && chs[l - 1] == chs[r]) {
                    l--;
                    r++;
                    continue;
                }
                break;
            }
            if (r - l > maxr - minl) {
                minl = l;
                maxr = r;
            }
        }
        return new String(chs, minl, maxr - minl);
    }




    static String createPalindrom(String p, int len) {
        // alpha: 97 ~ 122
        int a = 97;
        int z = 122;
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) (a + rd.nextInt(z - a)));
        }
        sb.insert(rd.nextInt(len), p);
        return sb.toString();
    }
    static String createLargePalindrom(int len) {
        int a = 97;
        int z = 122;
        Random rd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) (a + rd.nextInt(z - a)));
        }
        char[] reverse = sb.toString().toCharArray();
        for (int i = 0; i <= (len - 1) / 2; i++) {
            char x = reverse[i];
            reverse[i] = reverse[len - i - 1];
            reverse[len - i - 1] = x;
        }
        return sb.append(new String(reverse)).toString();
    }
}