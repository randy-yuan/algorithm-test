package randy.leetcode;

// https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/
// 167. 两数之和 II - 输入有序数组
public class TwoSumInSortedArray {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                result[0] = i + 1;
                result[1] = j + 1;
                return result;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }
}
