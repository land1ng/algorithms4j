package leetcode;

/**
 * @author Ran.Ding
 * @since 2022/8/19
 */
public class FindMedianSortedArrays {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int[] medians = new int[2];
        for (int i = 0, j = 0; i + j <= len / 2; ) {
            if (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    medians[(i + j) % 2] = nums1[i++];
                } else {
                    medians[(i + j) % 2] = nums2[j++];
                }
            } else if (i < nums1.length) {
                medians[(i + j) % 2] = nums1[i++];
            } else if (j < nums2.length) {
                medians[(i + j) % 2] = nums2[j++];
            }
        }
        double median;
        if (len % 2 == 0) {
            median = (medians[0] + medians[1]) / 2.0;
        } else {
            median = medians[((len / 2) % 2)];
        }
        return median;
    }
}
