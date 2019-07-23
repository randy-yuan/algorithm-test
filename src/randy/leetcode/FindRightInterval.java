package randy.leetcode;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode-cn.com/problems/find-right-interval/
// 436. 寻找右区间
public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0];
        } else if (intervals.length == 1) {
            return new int[]{-1};
        }

        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            ends[i] = intervals[i][1];
            intervals[i][1] = i;
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] res = new int[ends.length];
        for (int i = 0; i < ends.length; i++) {
            res[i] = search(intervals, ends[i]);
        }

        return res;
    }

    private int search(int[][] intervals, int target) {
        int begin = 0;
        int end = intervals.length - 1;
        int mid = 0;
        while (begin <= end) {
            mid = begin + (end - begin) / 2;
            if (target == intervals[mid][0]) {
                return intervals[mid][1];
            } else if (target < intervals[mid][0]) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return begin != intervals.length ? intervals[begin][1] : -1;
    }
}
