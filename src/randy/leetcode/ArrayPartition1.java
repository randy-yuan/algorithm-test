package randy.leetcode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/array-partition-i/
// 561. 数组拆分 I
public class ArrayPartition1 {
    public int arrayPairSum(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);

        int res = 0;
        for (int i = 0; i < nums.length; i += 2)
            res += nums[i];
        return res;
    }
}
