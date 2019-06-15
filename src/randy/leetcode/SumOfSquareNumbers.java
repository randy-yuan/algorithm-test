package randy.leetcode;

// https://leetcode-cn.com/problems/sum-of-square-numbers/
// 633. 平方数之和
public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int c) {
        // 0考虑c本身就是平方数, a == b是考虑了负数的存在
        int a = 0;
        int b = (int)Math.sqrt(c);
        int sum;
        while (a <= b) {
            sum = a * a + b * b;
            if (sum == c) return true;
            if (sum < c) a++;
            else b--;
        }
        return false;
    }
}
