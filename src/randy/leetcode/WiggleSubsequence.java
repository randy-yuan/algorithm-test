package randy.leetcode;

// https://leetcode-cn.com/problems/wiggle-subsequence/
// 376. 摆动序列
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i],down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i],up[j] + 1);
                }
            }
        }
        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return Math.max(down, up);
    }

    public static void main(String[] args) {
        WiggleSubsequence test = new WiggleSubsequence();
        System.out.println(test.wiggleMaxLength(new int[]{1,7,4,9,2,5}));
        System.out.println(test.wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8}));
        System.out.println(test.wiggleMaxLength(new int[]{1,2,3,4,5,6,7,8,9}));
    }
}
