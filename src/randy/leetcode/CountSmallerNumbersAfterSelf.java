package leetcode;

import java.util.*;

// 315 https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/
public class CountSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Set<Integer> set = new HashSet<>();
        for (int n: nums) set.add(n);

        int i = 0;
        int[] a = new int[set.size()];
        for (int n: set) a[i++] = n;
        Arrays.sort(a);

        BitTree bitTree = new BitTree(a.length);
        int[] ans = new int[nums.length];
        for (int j = nums.length - 1; j >= 0; j--) {
            int n = Arrays.binarySearch(a, nums[j]);
            ans[j] = bitTree.get(n);
            bitTree.add(n + 1, 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int j: ans) res.add(j);
        return res;
    }

    public List<Integer> countSmaller2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        // 索引数组
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) index[i] = i;

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) res.add(0);
        mergeSort(nums, 0, nums.length - 1, index, res);

        return res;
    }

    private void mergeSort(int[] nums, int l, int r, int[] index, List<Integer> res) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(nums, l, m, index, res);
            mergeSort(nums, m + 1, r, index, res);
            mergeTwo(nums, l, m, r, index, res);
        }
    }

    // [l, m]为左边排好序的, [m + 1, r]为右边排好序的
    private void mergeTwo(int[] nums, int l, int m, int r, int[] index, List<Integer> res) {
        int[] sorted = new int[r - l + 1];

        int i = l;
        int j = m + 1;
        for (int k = 0; k < sorted.length; k++) {
            // 遇到右边没有数或者大于等于左边的数，则从右边开始到此位置（不包含）的数都比左边当前的数小
            if (j > r || i <= m && nums[index[i]] <= nums[index[j]]) {
                res.set(index[i], res.get(index[i]) + j - m - 1);
                sorted[k] = index[i++];
            } else {
                sorted[k] = index[j++];
            }
        }

        System.arraycopy(sorted, 0, index, l, sorted.length);
    }

    static class BitTree {
        int[] val;

        public BitTree(int n) {
            val = new int[n + 1];
        }

        public int get(int n) {
            int v = 0;
            while (n > 0) {
                v += val[n];
                n -= n & -n;
            }
            return v;
        }

        public void add(int n, int v) {
            while (n < val.length) {
                val[n] += v;
                n += n & -n;
            }
        }
    }

    public static void main(String[] args) {
        CountSmallerNumbersAfterSelf countSmaller = new CountSmallerNumbersAfterSelf();
        List<Integer> res = countSmaller.countSmaller(new int[]{5, 2, 6, 1});
        List<Integer> res2 = countSmaller.countSmaller2(new int[]{5, 2, 6, 1});
        System.out.print(res.size());
    }
}
