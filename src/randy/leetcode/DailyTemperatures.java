package randy.leetcode;

import java.util.TreeSet;

// https://leetcode-cn.com/problems/daily-temperatures/
// 739. 每日温度
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] T) {
        if (T.length == 0) return T;
        if (T.length == 1) return new int[]{0};

        int[] r = new int[T.length];
        for (int i = 0; i < r.length; i++) {
            int j = i + 1;
            for (; j < r.length && T[j] <= T[i]; j++);
            if (j < r.length) r[i] = j - i;
            else r[i] = 0;
        }

        return r;
    }

    public static int[] dailyTemperatures2(int[] T) {
        int[] stack = new int[T.length];
        int top = -1;
        int[] res = new int[T.length];
        for(int i = 0; i < T.length; i++){
            while (top != -1 && T[i] > T[stack[top]]) {
                res[stack[top]] = i - stack[top--];
            }
            stack[++top] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] t = {73,74,75,71,69,72,76,73};
        PrintUtils.print(dailyTemperatures2(t));
    }
}
