package randy.leetcode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/
// 532. 数组中的K-diff数对
public class KDiffPairsInArray {
    public int findPairs(int[] nums, int k) {
        if (nums.length <= 1 || k < 0) return 0;
        int res = 0;
        Set<Integer> set = new HashSet();
        for (int i: nums) {
            set.add(i + k);
        }
        if (k == 0) {
            return Math.min(set.size(), nums.length - set.size());
        }
        for (int i : nums) {
            if (set.contains(i)) {
                res++;
                set.remove(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        KDiffPairsInArray kDiffPairs = new KDiffPairsInArray();
        int[] nums = {5,2,5,2,6,5,8,3,2,0};
        System.out.println(kDiffPairs.findPairs(nums, 2));
    }
}
