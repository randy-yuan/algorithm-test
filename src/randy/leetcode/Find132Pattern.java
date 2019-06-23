package randy.leetcode;

// https://leetcode-cn.com/problems/132-pattern/
// 456. 132模式
public class Find132Pattern {
    public boolean find132pattern(int[] nums) {
        int len = nums.length;
        int top = -1;
        int[] stack = new int[len];
        int second = Integer.MIN_VALUE;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] < second) {
                return true;
            }
            while (top != -1 && stack[top] < nums[i]) {
                second = stack[top--];
            }
            stack[++top] = nums[i];
        }
        return false;
    }

    public boolean find132pattern2(int[] nums) {
        if (nums.length < 3) return false;
        int len = 0;
        int[] a = new int[nums.length];
        int i = nums[0];
        int j = i;
        for (int k = 1; k < nums.length; k++) {
            for (int m = 0; m < len; m += 2) {
                if (a[m] < nums[k] && nums[k] < a[m+1])
                    return true;
            }
            if (nums[k] < j) {
                if (i < nums[k] && nums[k] < j)
                    return true;
                len = merge(a, len, i, j);
                i = nums[k];
                j = i;
            } else {
                j = nums[k];
            }
        }
        return false;
    }

    private int merge(int[] a, int len, int i, int j) {
        int idx = 0;
        for (int k = 0; k < len; k += 2) {
            if (i <= j) {
                if (i >= a[k]) {
                    i = a[k];
                    j = Math.max(j, a[k+1]);
                    continue;
                }
                if (i < a[k] && j >= a[k+1])
                    continue;
            }

            a[idx] = a[k];
            a[idx+1] = a[k+1];
            idx += 2;
        }

        if (i < j) {
            a[idx] = i;
            a[idx + 1] = j;
            idx += 2;
        }
        return idx;
    }

    public boolean find132pattern3(int[] nums) {
        if (nums.length < 3)
            return false;
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            min = Math.min(min, nums[i]); // 找到最小值即1
            if (min == nums[i]) // 若最小值为当前值则进行下一次遍历
                continue;
            for (int j = nums.length - 1; j > i; j--) {
                if (min < nums[j] && nums[j] < nums[i]) // 若出现32则返回正确
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Find132Pattern find132Pattern = new Find132Pattern();
        int[] nums = {3,2,1};
        System.out.println(find132Pattern.find132pattern2(nums));
    }
}
