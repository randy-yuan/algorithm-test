package randy.leetcode;

// https://leetcode-cn.com/problems/search-a-2d-matrix/
// 74. 搜索二维矩阵
public class Search2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;

        int start = 0;
        int end = m * n - 1;
        int mid;
        int r, c;
        while (start <= end) {
            mid = start + (end - start) / 2;
            r = mid / n;
            c = mid % n;
            if (target == matrix[r][c]) return true;
            else if (target < matrix[r][c]) end = mid - 1;
            else start = mid + 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {{1,   3,  5,  7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(searchMatrix(a, 3));
    }
}
