package randy.leetcode;

// https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
// 674. 最长连续递增序列
public class LongestContinuousIncreasingSubSequence {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;
        int cnt = 1;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) cnt++;
            else {
                if (cnt > res) res = cnt;
                cnt = 1;
            }
        }
        return cnt > res ? cnt : res;
    }
}
