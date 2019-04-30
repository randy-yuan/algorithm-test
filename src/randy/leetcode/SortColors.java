package randy.leetcode;

// https://leetcode-cn.com/problems/sort-colors/
// 75. 颜色分类
public class SortColors {
    public static void sortColors(int[] nums) {
        if (nums.length == 0) return;
        quickSort(nums, 0, nums.length);
    }

    public static void quickSort(int[] a, int start, int end) {
        if (start >= end) return;
        int m = a[start];
        int i = start;
        int j = end - 1;
        for (; i < j; ) {
            for (; i < j && a[j] > m; j--);
            a[i] = a[j];
            for (; i < j && a[i] <= m; i++);
            a[j] = a[i];
        }
        a[i] = m;

        quickSort(a, start, i);
        quickSort(a, i + 1, end);
    }
}
