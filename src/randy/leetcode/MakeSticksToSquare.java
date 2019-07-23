package randy.leetcode;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/matchsticks-to-square/
 * 473. 火柴拼正方形
 *
 * 注意:
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 */
public class MakeSticksToSquare {
    public static boolean makesquare(int[] nums) {
        if (nums.length < 4)
            return false;

        long sum = 0;
        for (int i: nums) sum += i;
        if (sum % 4 != 0)
            return false;

        long avg = sum >> 2;
        Arrays.sort(nums);
        if (nums[nums.length-1] > avg)
            return false;

        int i = 0;
        int j = nums.length - 1;
        long len = avg;
        while (i <= j) {
            if (len < nums[i])
                return false;
            if (len >= nums[j])
                len -= nums[j--];
            else
                len -= nums[i++];
            if (len == 0)
                len = avg;
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {5,5,5,5,4,4,4,4,3,3,3,3};
        System.out.println(makesquare(nums));
    }
}
