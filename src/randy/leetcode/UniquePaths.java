package randy.leetcode;

// https://leetcode-cn.com/problems/unique-paths/
// 62. 不同路径
public class UniquePaths {
  public static int uniquePaths(int m, int n) {
    if (m < 1 || n < 1) return 0;
    int[][] dp = new int[m][n];
    dp[0][0] = 1;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i > 0) dp[i][j] += dp[i-1][j];
        if (j > 0) dp[i][j] += dp[i][j-1];
      }
    }

    return dp[m-1][n-1];
  }

  public static void main(String[] args) {
    System.out.println(uniquePaths(3, 2));
  }

}
