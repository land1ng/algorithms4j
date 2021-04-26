package meetcode;

/**
 * @author dingdong
 * @since 2021/4/26
 */
public class SymmetricString {

    public static void main(String[] args) {
        System.out.println(isSymmetric("abba"));
        System.out.println(isSymmetric("acbbca"));
        System.out.println(isSymmetric("aba"));
        System.out.println(isSymmetric("abaa"));
    }

    public static boolean isSymmetric(String s) {
        for (int l = 0, r = s.length() - 1; l < r; l++, r--) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
        }
        return true;
    }
}
