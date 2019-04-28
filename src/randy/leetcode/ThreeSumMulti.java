package randy.leetcode;

import java.util.Arrays;

/* https://leetcode-cn.com/problems/3sum-with-multiplicity/
   923. 三数之和的多种可能

   提示：
   1. 3 <= A.length <= 3000
   2. 0 <= A[i] <= 100
   3. 0 <= target <= 300
 */
public class ThreeSumMulti {
    public static void main(String[] args) {
        int[] a = {1,1,2,2,3,3,4,4,5,5};
        System.out.println(threeSumMulti(a, 8));

        int[] a2 = {1,1,2,2,2,2};
        System.out.println(threeSumMulti(a2, 5));
        System.out.println(threeSumMulti2(a2, 5));
    }

    // 充分利用已知条件
    public static int threeSumMulti(int[] a, int target) {
        if (a.length == 0) return 0;
        long base = 1000000000 + 7;
        long count = 0;

        // 0 <= a[i] <= 100, 利用此条件实现相同数的合并计数并排序
        int[] an = new int[101];
        for (int i = 0; i < a.length; i++) {
            an[a[i]]++;
        }

        int end = an.length - 1;
        for (; end > 0 && an[end] == 0; end--);
        for (int i = 0; i < an.length; i++) {
            for (; i < an.length && an[i] == 0; i++);
            if (i >= an.length) break;

            if (an[i] >= 3 && 3 * i == target) {
                for (int j = an[i] - 1; j > 1; j--) {
                    count += j * (j - 1) / 2;
                }
            }

            if (an[i] >= 2) {
                int n = target - i - i;
                if (n > i && n < an.length && an[n] > 0) {
                    count += an[n] * an[i] * (an[i] - 1) / 2;
                }
            }

            int n = target - i;
            int j = i + 1;
            int k = end;
            for (; j < k; ) {
                for (; j < k && an[j] == 0; j++);
                for (; j < k && an[k] == 0; k--);
                if (j == k) break;

                if ((j + k) < n) j++;
                else if ((j + k) > n) {
                    if (an[k] >= 2 && (k + k) == n) {
                        count += an[i] * an[k] * (an[k] - 1) / 2;
                        break;
                    }
                    k--;
                } else count += an[i] * an[j++] * an[k--];
            }
            if (j == k && an[j] >= 2 && (j + j) == n) {
                count += an[i] * an[j] * (an[j] - 1) / 2;
            }
        }

        return (int)(count % base);
    }

    public static int threeSumMulti2(int[] a, int target) {
        if (a.length == 0) return 0;
        long base = 1000000000 + 7;
        long count = 0;
        int len = a.length;

        Arrays.sort(a);
        for (int i = 0; i < len - 2; i++) {
            int j = i + 1;
            int k = len - 1;
            int n = target - a[i];
            while (j < k) {
                if ((a[j] + a[k]) < n) j++;
                else if ((a[j] + a[k]) > n) k--;
                else if (a[j] == a[k]) {
                    count += (k - j) * (k - j + 1) / 2;
                    //if (count >= base) count %= base;
                    break;
                } else {
                    int aj = a[j++];
                    int cj = 1;
                    for (; j < k && a[j] == aj; j++, cj++) ;

                    int ak = a[k--];
                    int ck = 1;
                    for (; j <= k && a[k] == ak; k--, ck++) ;

                    count += cj * ck;
                    //if (count >= base) count %= base;
                }
            }
        }
        return (int)(count % base);
    }
}
