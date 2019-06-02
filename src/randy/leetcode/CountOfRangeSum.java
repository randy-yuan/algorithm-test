package randy.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/count-of-range-sum/
// 327. 区间和的个数
public class CountOfRangeSum {
    class BitTree {
        int[] val;

        BitTree(int n) {
            val = new int[n+1];
        }

        int get(int idx) {
            rangeCheck(idx);
            int result = 0;
            while (idx > 0) {
                result += val[idx];
                idx -= idx & -idx;
            }
            return result;
        }

        void add(int idx, int v) {
            rangeCheck(idx);
            while (idx < val.length) {
                val[idx] += v;
                idx += idx & -idx;
            }
        }

        void rangeCheck(int idx) {
            if (idx < 0 || idx >= val.length) {
                throw new IndexOutOfBoundsException("index out of bounds: " + idx);
            }
        }
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        if (len == 0) return 0;

        long[] sum = new long[len];
        long[] sa = new long[3 * len];
        for (int i = 0, j = 0; i < len; i++, j += 3) {
            sum[i] = i == 0 ? nums[i] : sum[i-1] + nums[i];
            sa[j] = sum[i];
            sa[j + 1] = sum[i] + lower;
            sa[j + 2] = sum[i] + upper;
        }
        Arrays.sort(sa);

        int n = 0;
        Map<Long, Integer> valMap = new HashMap<>();
        for (long i: sa) {
            if (!valMap.containsKey(i)) {
                valMap.put(i, ++n);
            }
        }

        int result = 0;
        BitTree bt = new BitTree(n);
        for (int i = len - 1; i >= 0; i--) {
            if (sum[i] >= lower && sum[i] <= upper) result++;
            int l = valMap.get(sum[i] + lower);
            int r = valMap.get(sum[i] + upper);
            result += bt.get(r) - bt.get(l-1);
            bt.add(valMap.get(sum[i]), 1);
        }

        return result;
    }

    public int countRangeSum2(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
                sum[i] = i == 0 ? nums[i] : sum[i-1] + nums[i];
        }
        return mergeCount(sum, 0, nums.length, lower, upper);
    }

    private int mergeCount(long[] sum, int start, int end, int lower, int upper) {
        if (start >= end) return 0;
        if (start == end - 1) {
            return sum[start] >= lower && sum[start] <= upper ? 1 : 0;
        }

        int mid = start + (end - start) / 2;
        int result = mergeCount(sum, start, mid, lower, upper)
                + mergeCount(sum, mid, end, lower, upper);

        int i = mid;
        int j = mid;
        for (int k = start; k < mid; k++) {
            while (i < end && sum[i] - sum[k] < lower) i++;
            while (j < end && sum[j] - sum[k] <= upper) j++;
            result += j - i;
        }

        merge(sum, start, end, mid);
        return result;
    }

    private void merge(long[] sum, int start, int end, int mid) {
        long[] tmp = new long[end-start];
        int i = start;
        int j = mid;
        int k = 0;
        while (i < mid && j < end) {
            if (sum[i] <= sum[j]) tmp[k++] = sum[i++];
            else tmp[k++] = sum[j++];
        }
        for (; i < mid; i++) tmp[k++] = sum[i];
        for (; j < end; j++) tmp[k++] = sum[j];
        System.arraycopy(tmp, 0, sum, start, tmp.length);
    }

    public int countRangeSum1(int[] nums, int lower, int upper) {
        int count = 0;
        long sum;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= lower && sum <= upper) count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        CountOfRangeSum crs = new CountOfRangeSum();
        int[] nums = {-2, 5, -1};
        //System.out.println(crs.countRangeSum2(nums, -2, 2));

        int[] nums2 = {-1, 1};
        System.out.println(crs.countRangeSum2(nums2, 0, 0));
    }
}
