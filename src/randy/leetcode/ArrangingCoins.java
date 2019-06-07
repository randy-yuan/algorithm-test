package randy.leetcode;

// https://leetcode-cn.com/problems/arranging-coins/
// 441. 排列硬币
public class ArrangingCoins {
    public static int arrangeCoins(int n) {
        if (n <= 1) return n;
        long begin = 1;
        long end = 1 << 16;
        long mid;
        long m;
        while (begin <= end) {
            mid = (begin + end) / 2;
            m = (mid * mid + mid) >> 1;
            if (m == n) return (int)mid;
            if (m > n) end = mid - 1;
            else begin = mid + 1;
        }
        return (int)begin - 1;
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoins(1804289383));
    }
}
