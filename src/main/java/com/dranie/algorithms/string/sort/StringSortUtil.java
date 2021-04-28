package com.dranie.algorithms.string.sort;

import com.dranie.algorithms.sort.utils.SortUtil;
import com.dranie.algorithms.utils.Assert;
import lombok.experimental.UtilityClass;

/**
 * 字符串排序工具类
 *
 * @author dingdong
 * @since 2021/4/27
 */
@UtilityClass
public class StringSortUtil {

    /**
     * 适用于字符串的插入排序
     * <p>
     * 根据字符串第d个字符对指定范围的字符串进行排序
     *
     * @param a  待排序数组
     * @param lo 左边界
     * @param hi 右边界
     * @param d  第几个位置
     */
    public static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                if (less(a, j, j - 1, d)) {
                    SortUtil.exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static boolean less(String[] a, int i, int j, int d) {
        Assert.isTrue(i < a.length);
        Assert.isTrue(j < a.length);
        int keyi = charAt(a[i], d);
        int keyj = charAt(a[j], d);
        return keyi < keyj;
    }

    public static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }
}
