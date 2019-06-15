package randy.leetcode;

// https://leetcode-cn.com/problems/reshape-the-matrix/
// 566. 重塑矩阵
public class ReshapeTheMatrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length;
        int n = nums[0].length;
        if (m * n != r * c) return nums;

        int[][] res = new int[r][c];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int idx = i * n + j;
                res[idx / c][idx % c] = nums[i][j];
            }
        }
        return res;
    }
}
