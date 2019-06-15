package randy.leetcode;

// https://leetcode-cn.com/problems/non-decreasing-array/
// 665. 非递减数列

/**
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
 * 参考 {@link ShortestUnsortedContinuousSubArray}的方法
 * 找到拐点, 统计修改次数
 */
public class NonDecreasingArray {
    public boolean checkPossibility(int[] nums) {
        if (nums.length == 0) return true;
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] <= nums[i]) continue;
            if (i > 1 && nums[i-2] > nums[i]) {
                nums[i] = nums[i-1];
            }
            if (++cnt > 1) return false;
        }
        return true;
    }
}
