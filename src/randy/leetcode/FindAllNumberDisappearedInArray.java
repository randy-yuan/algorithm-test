package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
// 448. 找到所有数组中消失的数字
public class FindAllNumberDisappearedInArray {
    // 无额外空间，O(N)的优化，利用负数来标记位置，同时还可以取到原值，不影响后续标记
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            nums[idx] = -Math.abs(nums[idx]);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) res.add(i+1);
        }
        return res;
    }

    // O(N)空间，利用出现过的值标记对应索引位置
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        boolean[] flag = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            flag[nums[i]-1] = true;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!flag[i]) res.add(i+1);
        }
        return res;
    }
}
