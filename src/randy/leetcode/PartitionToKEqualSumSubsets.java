package randy.leetcode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/
// 698. 划分为k个相等的子集
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        if (nums.length < k) {
            return false;
        }

        int sum = 0;
        for (int i: nums) {
            sum += i;
        }
        if (sum % k != 0) {
            return false;
        }

        int avg = sum / k;
        Arrays.sort(nums);
        if (nums[nums.length-1] > avg) {
            return false;
        }

        boolean[] flag = new boolean[16];
        for (int i = 0; i < k; i++) {
            if (!partition(nums, flag, avg)) {
                return false;
            }
        }

        return true;
    }

    private boolean partition(int[] nums, boolean[] flag, int target) {
        int[] stk = new int[nums.length];
        int top = -1;
        int sum = 0;
        stk[++top] = -1;

        while (top >= 0) {
            int last = stk[top];
            int i = last + 1;
            for (; i < 16 && flag[i]; i++);
            if (i < 16 && (sum + nums[i]) <= target) {
                sum += nums[i];
                flag[i] = true;
                if (sum < target) {
                    stk[++top] = i;
                } else {
                    return true;
                }
            } else {
                if (top > 1) {
                    sum -= nums[last];
                    flag[last] = false;
                    top--;
                } else {
                    return false;
                }
            }
        }

        return sum == target;
    }

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets partition = new PartitionToKEqualSumSubsets();
        System.out.println(partition.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4));
    }
}
