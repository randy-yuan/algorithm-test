package randy.leetcode;

// https://leetcode-cn.com/problems/spiral-matrix-ii/
// 59. 螺旋矩阵 II
public class SpiralMatrix2 {
    public static int[][] generateMatrix(int n) {
        if (n <= 0) return null;

        int[][] a = new int[n][n];
        int rs = 0, cs = -1;
        int re = n, ce = n;
        int ri = 1, ci = 1;
        int r = 0, c = -1;
        int i = 1;

        for (;;) {
            if ((ce - cs) < 2) break;
            for (c += ci; c > cs && c < ce; c += ci) a[r][c] = i++;
            if (ci == 1) {
                ce--;
                c--;
                ci = -1;
            } else {
                cs++;
                c++;
                ci = 1;
            }

            if ((re - rs) < 2) break;
            for (r += ri; r > rs && r < re; r += ri) a[r][c] = i++;
            if (ri == 1) {
                re--;
                r--;
                ri = -1;
            } else {
                rs++;
                r++;
                ri = 1;
            }
        }

        return a;
    }

    public static void main(String[] args) {
        int[][] a = generateMatrix(5);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
