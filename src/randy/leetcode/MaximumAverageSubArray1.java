package randy.leetcode;

// https://leetcode-cn.com/problems/maximum-average-subarray-i/
// 643. 子数组最大平均数 I
public class MaximumAverageSubArray1 {
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        double maxSum;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum / k;
    }
}
