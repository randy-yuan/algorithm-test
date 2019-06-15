package randy.leetcode;

// https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
// 581. 最短无序连续子数组
public class ShortestUnsortedContinuousSubArray {
    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        int j = nums.length - 1;
        for (; i < j && nums[i] <= nums[i+1]; i++);
        for (; i < j && nums[j] >= nums[j-1]; j--);
        if (i == j) return 0;

        int min = nums[i];
        int max = nums[i];
        for (int k = i; k <= j; k++) {
            if (nums[k] < min) min = nums[k];
            if (nums[k] > max) max = nums[k];
        }

        i = search(nums, 0, i, min);
        for (; i < j && nums[i] == min; i++);

        j = search(nums, j, nums.length - 1, max);
        if (j < nums.length) {
            for (; i < j && nums[j-1] == max; j--);
        }

        return j - i;
    }

    private int search(int[] nums, int start, int end, int t) {
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (t == nums[mid]) return mid;
            else if (t > nums[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return start;
    }

    public static void main(String[] args) {
        ShortestUnsortedContinuousSubArray subArray = new ShortestUnsortedContinuousSubArray();
        int[] nums = {2,6,4,8,10,9,15};
        int[] nums2 = {2,3,3,2,4};
        int[] nums3 = {1,3,2,3,3};
        //System.out.println(subArray.findUnsortedSubarray(nums));
        //System.out.println(subArray.findUnsortedSubarray(nums2));
        System.out.println(subArray.findUnsortedSubarray(nums3));
    }
}
