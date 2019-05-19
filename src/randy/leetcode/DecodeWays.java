package randy.leetcode;

// https://leetcode-cn.com/problems/decode-ways/
// 91. 解码方法
public class DecodeWays {
    public int numDecodings(String s) {
        int length = s.length();
        if (length == 0) return 0;
        if (s.charAt(0) == '0') return 0;

        int[] dp = new int[length+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) != '0') dp[i+1] += dp[i];
            if (isTwoValid(s, i)) dp[i+1] += dp[i-1];
            if (dp[i+1] == 0) return 0;
        }
        return dp[length];
    }

    private boolean isTwoValid(String s, int i) {
        int d = s.charAt(i - 1) - '0';
        int val = d * 10 + s.charAt(i) - '0';
        return d > 0 && val > 0 && val < 27;
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("226"));
    }
}
