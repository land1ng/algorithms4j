package leetcode2022;

import java.util.Arrays;

/**
 * @author RanYeah
 * @since 2022/8/23
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] num1 = new int[] { 1, 2, 3, 0, 0, 0 };
        int[] num2 = new int[] { 2, 5, 6 };
//        merge(num1, 3, num2, 3);
        mergeInPlace(num1, 3, num2, 3);
        System.out.println(Arrays.toString(num1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = 0;
        int[] merge = new int[m + n];
        for (int i = 0, j = 0; i < m || j < n; ) {
            if (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    merge[k++] = nums1[i];
                    i++;
                } else {
                    merge[k++] = nums2[j];
                    j++;
                }
            } else if (i < m) {
                merge[k++] = nums1[i++];
            } else if (j < n) {
                merge[k++] = nums2[j++];
            }
        }
        System.arraycopy(merge, 0, nums1, 0, m + n);
    }

    // 逆向双指针归并排序
    public static void mergeInPlace(int[] nums1, int m, int[] nums2, int n) {
        // nums1的尾部有足够空间
        int k = m + n - 1;
        for (int i = m - 1, j = n - 1; i >= 0 || j >= 0; ) {
            if (i >= 0 && j >= 0) {
                if (nums2[j] > nums1[i]) {
                    nums1[k--] = nums2[j--];
                } else {
                    nums1[k--] = nums1[i--];
                }
            } else if (i >= 0) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}
