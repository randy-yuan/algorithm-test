package randy.leetcode;

// https://leetcode-cn.com/problems/nth-digit/
// 400. 第N个数字
public class NthDigit {
    public static int findNthDigit(int n) {
// 9 * 10000 * 5 = 450000
// 9 * 1000 * 4 = 36000
// 9 * 100 * 3 = 2700
// 9 * 10 * 2 = 180
// 9 * 1 * 1 = 9

        int x = 1;
        int y = 1;
        long z = 9;
        while (n > z) {
            n -= z;
            x *= 10;
            y++;
            z = 9 * (long)x * y;
        }

        int d = x + (n - 1) / y;
        int m = y - (n - 1) % y - 1;
        for (int i = 0; i < m; i++) {
            d /= 10;
        }
        return d % 10;
    }

    public static void main(String[] args) {
        /*System.out.println(findNthDigit(1));
        System.out.println(findNthDigit(9));
        System.out.println(findNthDigit(10));
        System.out.println(findNthDigit(11));*/
        System.out.println(findNthDigit(1000000000));
    }
}
