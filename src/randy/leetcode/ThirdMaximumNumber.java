package randy.leetcode;

// https://leetcode-cn.com/problems/third-maximum-number/
// 414. 第三大的数
public class ThirdMaximumNumber {
  public static int thirdMax(int[] nums) {
    // 注意，要求返回第三大的数，是指第三大且唯一出现的数。
    // 存在两个值为2的数，它们都排第二。
    int len = nums.length;
    int cnt = 0;
    int i;
    for (i = 0; i < len && cnt < 3; i++) {
      for (int j = len - 1; j > i; j--) {
        if (nums[j] > nums[j-1]) {
          int tmp = nums[j];
          nums[j] = nums[j-1];
          nums[j-1] = tmp;
        }
      }
      if (i == 0 || nums[i] != nums[i-1]) cnt++;
    }
    return cnt == 3 ? nums[i-1] : nums[0];
  }

  public static int thirdMax2(int[] nums) {
    long first = Long.MIN_VALUE;
    long second = Long.MIN_VALUE;
    long third = Long.MIN_VALUE;
    for (int num : nums) {
      if (num > first) {
        third = second;
        second = first;
        first = num;
      } else if (num > second && num < first) {
        third = second;
        second = num;
      } else if (num > third && num < second) {
        third = num;
      }
    }
    return third == Long.MIN_VALUE ? (int)first : (int)third;
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 1};
    int[] nums2 = {1, 2};
    int[] nums3 = {2, 2, 3, 1};
    int[] nums4 = {1, 2, -2147483648};
    System.out.println(thirdMax(nums));
    System.out.println(thirdMax(nums2));
    System.out.println(thirdMax(nums3));
    System.out.println(thirdMax2(nums4));
  }
}
