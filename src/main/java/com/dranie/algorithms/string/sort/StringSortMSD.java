package com.dranie.algorithms.string.sort;

import java.util.Arrays;

/**
 * 高位优先字符串排序
 * <p>
 * 主要思想：
 * <p>
 * 首先用键索引计数法将所有字符串按照首字母排序，
 * 然后递归地将每个首字母所对应的子数组排序（忽略首字母，因为每一类的所有字符串的首字母都是相同的）。
 *
 * @author dingdong
 * @since 2021/4/27
 */
public class StringSortMSD implements StringSort {

    @Override
    public String name() {
        return "高位优先字符串排序";
    }

    private static final int R = 256;   // 基数

    private static final int M = 15;    // 小数组的切换阈值

    @Override
    public void sort(String[] a, int lo, int hi) {
        sort(a, lo, hi, 0, new String[a.length]);
    }

    @SuppressWarnings("SameParameterValue")
    private void sort(String[] a, int lo, int hi, int d, String[] aux) {
        // 以第d个字符为键将a[lo]至a[hi]排序
        if (hi <= lo + M) {
            StringSortUtil.insertion(a, lo, hi, d);
            return;
        }
        // 计算频率
        // count数组中每个索引的位置代表一个字符
        // 为什么长度为 R+2 ？因为
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        // 将频率转换为索引
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        // 数据分类
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        // 回写
        if (hi - lo >= 0) {
            System.arraycopy(aux, 0, a, lo, hi - lo);
        }
        // 递归的以每个字符为键进行排序
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
        }
    }

    private int charAt(String s, int d) {
        return StringSortUtil.charAt(s, d);
    }

    public static void main(String[] args) {
        String s = "Many of the designations used by manufacturers and sellers to distinguish their products are claimed as trademarks. Where those designations appear in this book, and the publisher was aware of a trademark claim, the designations have been printed with initial capital letters or in all capitals.";
        String[] a = Arrays.stream(s.split("\\s"))
                .filter(s0 -> !".".equals(s0) && !",".equals(s0))
                .map(s0 -> s0.replace(".", "").replace(",", ""))
                .toArray(String[]::new);
        System.out.println(Arrays.toString(a));
        new StringSortMSD().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
