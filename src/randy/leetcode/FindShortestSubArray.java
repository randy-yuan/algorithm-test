package randy.leetcode;

public class FindShortestSubArray {
  public static int findShortestSubArray(int[] nums) {
    if (nums.length == 0) return 0;

    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] > max) max = nums[i];
    }

    int[] d = new int[max+1];
    int[] start = new int[max+1];
    int[] end = new int[max+1];

    for (int i = 0; i < nums.length; i++) {
      if (d[nums[i]] == 0) start[nums[i]] = i;
      d[nums[i]]++;
      end[nums[i]] = i;
    }

    int maxd = 0;
    int n = 0;
    for (int i = 0; i <= max; i++) {
      if (d[i] > maxd) {
        maxd = d[i];
        n = i;
      } else if (d[i] == maxd && (end[i] - start[i]) < (end[n] - start[n])) {
        n = i;
      }
    }

    return end[n] - start[n] + 1;
  }

  public static void main(String[] args) {
    int[] a = {1, 2, 2, 3, 1};
    System.out.println(findShortestSubArray(a));
  }
}
