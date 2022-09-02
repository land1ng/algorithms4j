package leetcode2022.bit;

/**
 * [136]只出现一次的数字
 *
 * @author RanYeah
 * @since 2022/9/2
 */
public class SingleNumber {

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{ 2, 2, 1 }));
        System.out.println(singleNumber(new int[]{ 4, 1, 2, 1, 2 }));
        System.out.println(singleNumber(new int[]{ 1, 2, 1, 2, 3 }));
    }

    public static int singleNumber(int[] nums) {
        // a ^ 0 = a
        // a ^ a = 0
        // a ^ b ^ c = a ^ c ^ b 满足交换律
        for (int i = 1; i < nums.length; i++) {
            nums[i] ^= nums[i - 1];
        }
        return nums[nums.length - 1];
    }
}
