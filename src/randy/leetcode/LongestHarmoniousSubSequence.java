package randy.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/longest-harmonious-subsequence/
// 594. 最长和谐子序列
public class LongestHarmoniousSubSequence {
    // 没有说明一定是连续子序列，所以可以排序
    public int findLHS(int[] nums) {
        if (nums.length <= 1) return 0;
        Arrays.sort(nums);

        int maxLen = 0;
        int i = 0;
        int j = 0;
        while (i <= j && j < nums.length) {
            for (; j < nums.length && nums[j] <= nums[i] + 1; j++);
            if (nums[j-1] == nums[i] + 1) {
                maxLen = Math.max(maxLen, j - i);
            }
            i++;
        }
        return maxLen;
    }

    public int findLHS2(int[] nums) {
        if (nums.length <= 1) return 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int i: nums) {
            count.put(i, 1 + count.getOrDefault(i, 0));
        }

        int maxLen = 0;
        for (int i: nums) {
            int cnt = count.getOrDefault(i + 1, 0);
            if (cnt > 0) {
                maxLen = Math.max(maxLen, count.get(i) + cnt);
            }
        }
        return maxLen;
    }
}
