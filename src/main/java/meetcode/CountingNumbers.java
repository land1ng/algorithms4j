package meetcode;

import java.util.Arrays;

/**
 * @author dingdong
 * @since 2021/4/26
 */
public class CountingNumbers {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(count("11234567890")));
    }

    public static int[] count(String num) {
        int[] count = new int[10];
        for (int i = 0; i < num.length(); i++) {
            int n = Integer.parseInt(num.substring(i, i + 1));
            count[n]++;
        }
        return count;
    }
}
