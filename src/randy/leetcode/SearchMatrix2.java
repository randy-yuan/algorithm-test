package randy.leetcode;

// https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
// 240. 搜索二维矩阵 II
public class SearchMatrix2 {
  public boolean searchMatrix(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) return false;
    for (int i = 0; i < matrix.length; i++) {
      if (searchArray(matrix[i], target)) return true;
    }
    return false;
  }

  public boolean searchMatrix2(int[][] matrix, int target) {
    if (matrix.length == 0 || matrix[0].length == 0) return false;
    int m = matrix.length, n = matrix[0].length;
    int i = 0, j = n - 1;
    while (i < m && j >= 0) {
      if (target == matrix[i][j]) return true;
      else if (target < matrix[i][j]) j--;
      else i++;
    }
    return false;
  }

  public boolean searchArray(int[] a, int t){
    int start = 0;
    int end = a.length - 1;
    int mid;
    while (start <= end) {
      mid = start + (end - start) / 2;
      if (t == a[mid]) return true;
      else if (t < a[mid]) end = mid - 1;
      else start = mid + 1;
    }
    return false;
  }

  public int[] merge(int[] a, int[] b) {
    int[] c = new int[a.length + b.length];
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < a.length && j < b.length) {
      c[k++] = a[i] <= b[j] ? a[i++] : b[j++];
    }
    if (i < a.length) System.arraycopy(a, i, c, k, a.length - i);
    else System.arraycopy(b, j, c, k, b.length - j);
    return c;
  }
}
