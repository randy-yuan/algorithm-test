package randy.leetcode;

// https://leetcode-cn.com/problems/house-robber/
// 198. 打家劫舍
public class HouseRobber198 {
  public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    int dpPrev, dpCur;
    int dpUnRobbedPrev, dpUnRobbedCur;
    dpPrev = nums[0];
    dpUnRobbedPrev = 0;

    for (int i = 1; i < nums.length; i++) {
      dpCur = dpUnRobbedPrev + nums[i];
      dpUnRobbedCur = Math.max(dpPrev, dpUnRobbedPrev);
      dpPrev = dpCur;
      dpUnRobbedPrev = dpUnRobbedCur;
    }

    return Math.max(dpPrev, dpUnRobbedPrev);
  }

  public int rob2(int[] nums) {
    if (nums.length == 0) return 0;
    return rob(nums, 0);
  }

  private int rob(int[] nums, int start) {
    if (start >= nums.length) return 0;
    return Math.max(nums[start] + rob(nums, start + 2), rob(nums, start + 1));
  }

  public static void main(String[] args) {
    HouseRobber198 robber = new HouseRobber198();
    int[] nums = {1, 2, 3, 1};
    int[] nums2 = {2, 7, 9, 3, 1};
    int[] nums3 = {2, 1, 1, 2};
    System.out.println(robber.rob(nums));
    System.out.println(robber.rob(nums2));
    System.out.println(robber.rob(nums3));
  }
}
