package randy.leetcode;

// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
// 80. 删除排序数组中的重复项 II
public class RemoveDuplicatesFromSortedArray {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        int cnt = 0;
        int val = nums[0];
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = val;
                if (cnt > 1) {
                    nums[i++] = val;
                    cnt = 1;
                }
                val = nums[j];
            } else {
                cnt++;
            }
        }

        nums[i++] = val;
        if (cnt > 1) {
            nums[i++] = val;
        }

        return i;
    }
}
