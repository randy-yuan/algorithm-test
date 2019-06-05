package randy.leetcode;

// https://leetcode-cn.com/problems/number-of-1-bits/
// 191. 位1的个数
public class NumberOfOneBits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;
        }
        return count;
    }
}
