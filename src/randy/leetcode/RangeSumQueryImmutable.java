package randy.leetcode;

// https://leetcode-cn.com/problems/range-sum-query-immutable/
// 303. 区域和检索 - 数组不可变
public class RangeSumQueryImmutable {
    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(i,j);
     */
    class NumArray {
        long[] sum;

        public NumArray(int[] nums) {
            if (nums.length != 0) {
                sum = new long[nums.length];
                sum[0] = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    sum[i] = sum[i-1] + nums[i];
                }
            }
        }

        public int sumRange(int i, int j) {
            if (i > j || i < 0 || sum == null || j >= sum.length) return 0;
            return (int)(i == 0 ? sum[j] : sum[j] - sum[i-1]);
        }
    }
}
