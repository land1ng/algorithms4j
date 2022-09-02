package leetcode2022.arr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 合并重叠区间
 * <p>
 * [[1,3],[2,6],[8,10],[15,18]] => [[1,6],[8,10],[15,18]]
 *
 * @author Ran.Ding
 * @since 2022/9/2
 */
public class MergeOverlapIntervals {

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{
                {1, 4}, {0, 4}
        })));
        System.out.println(Arrays.deepToString(merge(new int[][]{
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        })));
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        List<int[]> merged = new ArrayList<>(intervals.length);
        // 这里如果用lambda会特别慢...
//        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        int l = intervals[0][0];
        int r = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= r) {
                r = Math.max(r, intervals[i][1]);
            } else {
                // 断了
                merged.add(new int[]{l, r});
                l = intervals[i][0];
                r = intervals[i][1];
            }
            if (i == intervals.length - 1) {
                // 末尾了
                merged.add(new int[]{l, r});
            }
        }
        return merged.toArray(new int[merged.size()][2]);
    }

    // 标准答案
    public static int[][] merge0(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
