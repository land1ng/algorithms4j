package leetcode2022.arr;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * [349]两个数组的交集
 *
 * @author RanYeah
 * @since 2022/9/2
 */
public class Intersection {

    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> inters = new HashSet<>();
        int l1 = 0, l2 = 0;
        while (l1 < nums1.length && l2 < nums2.length) {
            if (nums1[l1] > nums2[l2]) {
                l2++;
                continue;
            }
            if (nums1[l1] < nums2[l2]) {
                l1++;
                continue;
            }
            if (nums1[l1] == nums2[l2]) {
                inters.add(nums1[l1]);
                l1++;
                l2++;
            }
        }
        int[] ret = new int[inters.size()];
        int i = 0;
        for (int n : inters) {
            ret[i++] = n;
        }
        return ret;
    }
}
