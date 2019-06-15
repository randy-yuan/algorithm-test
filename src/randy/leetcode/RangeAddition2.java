package randy.leetcode;

// https://leetcode-cn.com/problems/range-addition-ii/
// 98. 范围求和 II
public class RangeAddition2 {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0) return m * n;
        int r = m;
        int c = n;
        for (int i = 0; i < ops.length; i++) {
            r = Math.min(r, ops[i][0]);
            c = Math.min(c, ops[i][1]);
        }
        return r * c;
    }
}
