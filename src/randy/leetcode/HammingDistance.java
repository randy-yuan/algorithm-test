package randy.leetcode;

// https://leetcode-cn.com/problems/hamming-distance/
// 461. 汉明距离
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int res = 0;
        while (z != 0) {
            res += z & 1;
            z >>>= 1;
        }
        return res;
    }
}
