package randy.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// https://leetcode-cn.com/problems/maximal-rectangle/
// 85. 最大矩形
public class MaximalRectangle {
    class Rectangle {
        int rs;
        int re;
        int cs;
        int ce;

        public Rectangle(int rs, int re, int cs, int ce) {
            this.rs = rs;
            this.re = re;
            this.cs = cs;
            this.ce = ce;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rectangle rectangle = (Rectangle) o;
            return rs == rectangle.rs &&
                    re == rectangle.re &&
                    cs == rectangle.cs &&
                    ce == rectangle.ce;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rs, re, cs, ce);
        }
    }

    Map<Rectangle, Integer> rectMap = new HashMap<>();

    public int maximalRectangle2(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        return maxRectangle(matrix, 0, matrix.length, 0, matrix[0].length);
    }

    public int maxRectangle(char[][] matrix, int rs, int re, int cs, int ce) {
        Rectangle rect = new Rectangle(rs, re, cs, ce);
        int max = rectMap.getOrDefault(rect, 0);
        if (max > 0) return max;
        for (int i = rs; i < re; i++) {
            for (int j = cs; j < ce; j++) {
                if (matrix[i][j] == '0') {
                    if (i > rs && (i - rs) * (ce - cs) > max)
                        max = Math.max(max, maxRectangle(matrix, rs, i, cs, ce));
                    if (i < re - 1 && (re - i - 1) * (ce - cs) > max)
                        max = Math.max(max, maxRectangle(matrix, i + 1, re, cs, ce));
                    if (j > cs && (re - rs) * (j - cs) > max)
                        max = Math.max(max, maxRectangle(matrix, rs, re, cs, j));
                    if (j < ce - 1 && (re - rs) * (ce - j - 1) > max)
                        max = Math.max(max, maxRectangle(matrix, rs, re, j + 1, ce));
                    rectMap.put(rect, max);
                    return max;
                }
            }
        }
        max = (re - rs) * (ce - cs);
        rectMap.put(rect, max);
        return max;
    }

    // 利用84题的结果
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int max = 0;
        int[] h = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < h.length; j++) {
                if (matrix[i][j] == '0') h[j] = 0;
                else h[j]++;
            }
            max = Math.max(max, LargestRectangleInHistogram.largestRectangleArea(h));
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] a = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        MaximalRectangle mr = new MaximalRectangle();
        System.out.println(mr.maximalRectangle(a));
    }
}
