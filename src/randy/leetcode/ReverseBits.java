package randy.leetcode;

// https://leetcode-cn.com/problems/reverse-bits/
// 190. 颠倒二进制位
public class ReverseBits {
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int m = 0;
        for (int i = 0; i < 32; i++) {
            m <<= 1;
            m |= n & 0x1;
            n >>>= 1;
        }
        return m;
    }

    public static void main(String[] args) {
        //System.out.println(reverseBits(43261596));
        //System.out.println(reverseBits(-3));
        System.out.println(reverseBits(Integer.valueOf("00000010100101000001111010011100", 2)));
    }
}
