package randy.leetcode;

// https://leetcode-cn.com/problems/unique-binary-search-trees/
// 96. 不同的二叉搜索树
public class UniqueBST {
    public static int numTrees(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += numTrees(i - 1) * numTrees(n - i);
        }
        return count;
    }

    public static int numTrees2(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= i; j++) {
                count += dp[j - 1] * dp[i - j];
            }
            dp[i] = count;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(UniqueBST.numTrees(3));
        System.out.println(UniqueBST.numTrees2(3));
    }
}
