package randy.leetcode;

// https://leetcode-cn.com/problems/volume-of-histogram-lcci/
public class VolumeOfHistogramICCI {
    public int trap(int[] height) {
        if (height == null || height.length < 3) return 0;

        int maxIdx = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > height[maxIdx]) maxIdx = i;
        }

        int max = height[maxIdx];
        int res = 0;
        int left = 0;
        for (int i = 0; i < maxIdx; i++) {
            if (height[i] > left) left = height[i];
            res += Math.min(left, max) - height[i];
        }

        int right = 0;
        for (int j = height.length - 1; j > maxIdx; j--) {
            if (height[j] > right) right = height[j];
            res += Math.min(right, max) - height[j];
        }

        return res;
    }

    // 本质上和上面思路一样，但是更简单，少了一次遍历
    // 每个位置能储存的水, 由左右两边最小的位置决定
    public int trap2(int[] height) {
        if(height == null || height.length < 3) return 0;

        int left = 0, right = height.length - 1;
        int leftMax = height[left], rightMax = height[right];
        int res = 0;

        while (left < right){
            if (leftMax < rightMax){
                res += leftMax - height[left++];
                leftMax = Math.max(height[left], leftMax);
            } else {
                res += rightMax - height[right--];
                rightMax = Math.max(height[right], rightMax);
            }
        }

        return res;
    }
}
