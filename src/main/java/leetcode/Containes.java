package leetcode;

/**
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * @author dingr
 * @since 2019-09-19
 */
public class Containes {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (needle.length() > haystack.length() - i)
                return -1;
            // 双向逼近
            int k = i, l = k + needle.length() - 1, m = 0, n = needle.length() - 1;
            for ( ; m <= n; m++, n--) {
                if (needle.charAt(m) != haystack.charAt(k++)) break;
                if (needle.charAt(n) != haystack.charAt(l--)) break;
            }
            if (m > n) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Containes().strStr("hadadldalllllallall", "lla"));
        System.out.println(new Containes().strStr("hadadllaallllallall", "laa"));
        // hadadldalllllallall
        // lla
    }
}