package randy.leetcode;

// 793. https://leetcode-cn.com/problems/preimage-size-of-factorial-zeroes-function/
public class PreimageSizeOfFactorialZeros {
    public int preimageSizeFZF(int K) {
        if (K == 0) return 5;
        long i = 0;
        long j = Long.MAX_VALUE;
        long m;
        long r;
        while (i < j) {
            m = i + (j - i) / 2;
            r = numberOfZero(m);
            if (r == K) {
                return 5;
            } else if (r < K) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return 0;
    }

    private long numberOfZero(long n) {
        long res = 0;
        while (n > 0) {
            n /= 5;
            res += n;
        }
        return res;
    }

    public static void main(String[] args) {
        PreimageSizeOfFactorialZeros test = new PreimageSizeOfFactorialZeros();
        test.preimageSizeFZF(5);
    }
}
