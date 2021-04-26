package meetcode;

/**
 * @author dingdong
 * @since 2021/4/26
 */
public class MaxOccursLetter {

    public static void main(String[] args) {
        System.out.println(maxOccurs("abb"));
        System.out.println(maxOccurs("aaa"));
        System.out.println(maxOccurs("aaabbbbccccc"));
    }

    public static char maxOccurs(String s) {
        if (s.length() <= 2) {
            return s.charAt(0);
        }
        int maxCnt = 1;
        char maxCh = s.charAt(0);
        int lastCnt = 1;
        char lastCh = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != lastCh) {
                if (lastCnt > maxCnt) {
                    maxCh = lastCh;
                    maxCnt = lastCnt;
                }
                lastCh = c;
            } else {
                lastCnt++;
                if (i == s.length() - 1) {
                    if (lastCnt > maxCnt) {
                        maxCh = lastCh;
                        maxCnt = lastCnt;
                    }
                }
            }
        }
        return maxCh;
    }
}
