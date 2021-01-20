package randy.leetcode;

// 321 https://leetcode-cn.com/problems/create-maximum-number/
public class CreateMaximumNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int i = 0;
        int j = 0;
        int m;
        for (int n = 0; n < k; n++) {
            m = nums1.length - i + nums2.length - j - k + n;

            int ii = i;
            for (int p = i; p < nums1.length && p <= (i + m); p++) {
                if (nums1[p] > nums1[ii]) ii = p;
            }

            int jj = j;
            for (int q = j; q < nums2.length && q <= (j + m); q++) {
                if (nums2[q] > nums2[jj]) jj = q;
            }

            if (nums1[ii] > nums2[jj]) {

            }
        }

        return res;
    }
}
