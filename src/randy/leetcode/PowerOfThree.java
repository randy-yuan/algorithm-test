package randy.leetcode;

// https://leetcode-cn.com/problems/power-of-three/
// 326. 3的幂
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        // int范围内最大的3次幂
        return n == 1 || 1162261467 % n == 0;
    }

    public boolean isPowerOfThree2(int n) {
        if (n < 1) return false;
        long x = 1;
        long y = n;
        while (x < y) {
            x *= 3;
        }
        return x == y;
    }
}
