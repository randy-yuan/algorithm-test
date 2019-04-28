package randy.leetcode;

// 按奇偶排序数组 II
// https://leetcode-cn.com/problems/sort-array-by-parity-ii/
public class SortArrayByParityII {
    public static void main(String[] args) {
        //int[] a = {2, 4, 3, 8, 5, 9, 0, 6, 7, 1};
        int[] a = {2, 4, 6, 8, 5, 7, 9, 1};
        SortArrayByParityII test = new SortArrayByParityII();
        PrintUtils.print(test.copy(a));
        PrintUtils.print(test.bubble(a));
        PrintUtils.print(test.partition(a));
    }

    public int[] copy(int[] a) {
        int[] r = new int[a.length];
        int i = 0;
        int j = 1;
        for (int k = 0; k < a.length; k++) {
            if (a[k] % 2 == 0) {
                r[i] = a[k];
                i += 2;
            } else {
                r[j] = a[k];
                j += 2;
            }
        }
        return r;
    }

    public int[] bubble(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if ((a[i] & 0x1) != (i & 0x1)) {
                int j = i + 1;
                for (; j < a.length && ((a[j] & 0x1) == (j & 0x1)
                        || (a[j] & 0x1) != (i & 0x1)); j++);
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        return a;
    }

    public int[] partition(int[] a) {
        int m = a[0];
        int i = 0;
        int j = a.length - 1;
        for (; i < j; ) {
            for (; i < j && (a[j] & 0x1) == 1; j--);
            a[i] = a[j];
            for (; i < j && (a[i] & 0x1) == 0; i++);
            a[j] = a[i];
        }
        a[i] = m;

        int half = a.length / 2;
        int offset = (half & 0x1) == 0 ? half - 1 : half;
        for (int k = 1; k < half; k += 2) {
            int tmp = a[k];
            a[k] = a[k+offset];
            a[k+offset] = tmp;
        }
        return a;
    }
}
