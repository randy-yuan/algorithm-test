package randy.leetcode;

// https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
// 84. 柱状图中最大的矩形
public class LargestRectangleInHistogram {
    // 木桶原理，遇到短的再处理前面的，可以利用栈的特性
    public static int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;

        int max = 0;
        int top = 0;
        int idx;
        int[] stk = new int[heights.length+1];
        stk[0] = -1;

        for (int i = 0; i < heights.length; i++) {
            while (top > 0 && heights[i] < heights[stk[top]]) {
                idx = stk[top--];
                max = Math.max(max, heights[idx] * (i - stk[top] - 1));
            }
            stk[++top] = i;
        }
        while (top > 0) {
            idx = stk[top--];
            max = Math.max(max, heights[idx] * (heights.length - stk[top] - 1));
        }

        return max;
    }

    // 分治策略
    public static int largestRectangleArea2(int[] heights) {
        if (heights.length == 0) return 0;
        return getMax(heights, 0, heights.length);
    }

    public static int getMax(int[] a, int start, int end) {
        if (start + 1 == end) return a[start];

        boolean sorted = true;
        int min = start;
        for (int i = start + 1; i < end; ++i) {
            if (a[i] < a[i - 1]) sorted = false;
            if (a[i] < a[min]) min = i;
        }

        int res = 0;
        if (sorted) {
            for (int i = start; i < end; ++i) {
                res = Math.max(res, a[i] * (end - i));
            }
            return res;
        }

        if (min > start) res = getMax(a, start, min);
        if (min < end - 1) res = Math.max(res, getMax(a, min + 1, end));

        return Math.max(res, a[min] * (end - start));
    }
}
