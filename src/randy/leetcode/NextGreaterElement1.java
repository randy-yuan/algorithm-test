package randy.leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/next-greater-element-i/
// 496. 下一个更大元素 I
public class NextGreaterElement1 {
    // 利用单调栈, 提前算好每个元素的下一个更大元素
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] stk = new int[nums2.length];
        int top = -1;
        for (int i = 0; i < nums2.length; i++) {
            while (top != -1 && stk[top] < nums2[i]) map.put(stk[top--], nums2[i]);
            stk[++top] = nums2[i];
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            idxMap.put(nums2[i], i);
        }

        int[] res = new int[nums1.length];
        int idx = -1;
        for (int i = 0; i < nums1.length; i++) {
            idx = idxMap.getOrDefault(nums1[i], -1);
            if (idx == -1) res[i] = -1;
            else {
                int j = idx + 1;
                for (; j < nums2.length && nums1[i] >= nums2[j]; j++);
                res[i] = j < nums2.length ? nums2[j] : -1;
            }
        }
        return res;
    }
}
