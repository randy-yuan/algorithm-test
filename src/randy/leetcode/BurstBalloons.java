package randy.leetcode;

// https://leetcode-cn.com/problems/burst-balloons/
// 312. 戳气球
public class BurstBalloons {
    // https://www.bilibili.com/video/av45180542
    // 二维动态规划, 逆向思考, 考虑最优子结构
    // dp[i][j]为[i, j]之间全部气球被戳爆所得最大硬币数量
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] m = new int[n + 2];
        m[0] = 1;
        m[n + 1] = 1;
        System.arraycopy(nums, 0, m, 1, n);

        for (int len = 1; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                // 逆向思维，假设k是[i, j]之间最后一个被戳爆的，则遍历过程中，左右2边的气球不变
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + dp[k+1][j] + m[k] * m[i-1] * m[j+1]);
                }
            }
        }
        return dp[1][n];
    }

    public int maxCoins2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] m = new int[n + 2];
        m[0] = 1;
        m[n + 1] = 1;
        System.arraycopy(nums, 0, m, 1, n);
        return maxCoins(m, 1, n, dp);
    }

    // [start, end]
    private int maxCoins(int[] a, int start, int end, int[][] dp) {
        if (start > end) return 0;
        if (dp[start][end] > 0) return dp[start][end];

        int result = 0;
        for (int i = start; i <= end; i++) {
            int coins = a[i] * a[start-1] * a[end+1] + maxCoins(a, start, i - 1, dp)
                    + maxCoins(a, i + 1, end, dp);
            if (coins > result) result = coins;
        }
        dp[start][end] = result;
        return result;
    }

    public int maxCoins3(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int coins;
        int maxCoins = 0;
        for (int i = 0; i < nums.length; i++) {
            int[] copy = new int[nums.length-1];
            if (i == 0) {
                coins = nums[i] * nums[i+1];
                System.arraycopy(nums, 1, copy, 0, copy.length);
            } else if (i == nums.length - 1) {
                coins = nums[i] * nums[i - 1];
                System.arraycopy(nums, 0, copy, 0, copy.length);
            } else {
                coins = nums[i] * nums[i-1] * nums[i+1];
                System.arraycopy(nums, 0, copy, 0, i);
                System.arraycopy(nums, i + 1, copy, i, copy.length - i);
            }

            coins += maxCoins(copy);
            if (coins > maxCoins) maxCoins = coins;
        }
        return maxCoins;
    }

    public static void main(String[] args) {
        BurstBalloons bb = new BurstBalloons();
        int[] nums = {3, 1, 5, 8, 7, 6, 9, 2, 4, 10, 15, 30, 20, 13, 17, 14, 16, 21, 39};

        long t1 = System.currentTimeMillis();
        int r1 = bb.maxCoins(nums);

        long t2 = System.currentTimeMillis();
        int r2 = bb.maxCoins2(nums);

        long t3 = System.currentTimeMillis();
        int r3 = bb.maxCoins3(nums);
        long t4 = System.currentTimeMillis();

        System.out.println("1. result=" + r1 + " " + (t2 - t1) + " ms");
        System.out.println("2. result=" + r2 + " " + (t3 - t2) + " ms");
        System.out.println("3. result=" + r3 + " " + (t4 - t3) + " ms");
    }
}
