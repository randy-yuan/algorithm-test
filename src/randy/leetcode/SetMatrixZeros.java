package randy.leetcode;

// https://leetcode-cn.com/problems/set-matrix-zeroes/
// 73. 矩阵置零
public class SetMatrixZeros {
  public static void setZeroes(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return;
    int m = matrix.length;
    int n = matrix[0].length;

    boolean r0 = false;
    boolean c0 = false;
    for (int i = 0; i < m; i++) {
      if (matrix[i][0] == 0) {
        c0 = true;
        break;
      }
    }
    for (int j = 0; j < n; j++) {
      if (matrix[0][j] == 0) {
        r0 = true;
        break;
      }
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    for (int i = 1; i < m; i++) {
      if (matrix[i][0] == 0) {
        for (int j = 1; j < n; j++) {
          matrix[i][j] = 0;
        }
      }
    }
    for (int j = 1; j < n; j++) {
      if (matrix[0][j] == 0) {
        for (int i = 1; i < m; i++) {
          matrix[i][j] = 0;
        }
      }
    }
    if (r0) {
      for (int j = 0; j < n; j++) {
        matrix[0][j] = 0;
      }
    }
    if (c0) {
      for (int i = 0; i < m; i++) {
        matrix[i][0] = 0;
      }
    }

  }

  public static void main(String[] args) {
    int[][] matrix = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
    setZeroes(matrix);
  }
}
