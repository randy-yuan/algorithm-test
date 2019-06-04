package randy.leetcode;

// 今日头条笔试题：(区间最小值 * 区间和) 的最大值 [单调栈]
// 给定一段数组，求每个区间的最小值乘这段区间的和，输出每个区间得到的最大值
public class MaxMinRangeSum {
    public static long calc(int[] nums) {
        if (nums.length == 0) return 0;
        int len = nums.length;
        long[] sum = new long[len+1];
        int[] start = new int[len+1];
        int[] end = new int[len+1];

        sum[0] = 0;
        for (int i = 0; i < len; i++) {
            sum[i+1] = sum[i] + nums[i];
            start[i] = i;
            end[i] = i;
        }

        int[] stk = new int[len];
        int top = -1;
        int i = 0;
        int n;
        while (i <= len) {
            n = i < len ? nums[i] : 0;
            if (top == -1 || n > nums[stk[top]]) {
                if (top != -1) {
                    start[i] = stk[top] + 1;
                }
                stk[++top] = i++;
            } else {
                end[stk[top]] = i - 1;
                start[i] = start[stk[top]];
                top--;
            }
        }

        long result = 0;
        long cur = 0;
        for (i = 0; i < len; i++) {
            cur = nums[i] * (sum[end[i]+1] - sum[start[i]]);
            result = Math.max(cur, result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6};
        System.out.println(calc(nums));
    }
}
