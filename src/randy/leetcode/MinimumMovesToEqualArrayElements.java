package randy.leetcode;

// https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/
// 453. 最小移动次数使数组元素相等
// n-1个加1，相当于1个减1
public class MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        if (nums.length == 0) return 0;
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) min = nums[i];
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != min) {
                res += nums[i] - min;
            }
        }
        return res;
    }
}
