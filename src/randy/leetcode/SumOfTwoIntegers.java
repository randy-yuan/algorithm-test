package randy.leetcode;

// https://leetcode-cn.com/problems/sum-of-two-integers/
// 371. 两整数之和
// 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和
public class SumOfTwoIntegers {
  public static int getSum(int a, int b) {
    int c;
    while (b != 0) {
      c = a ^ b;
      b = (a & b) << 1;
      a = c;
    }
    return a;
  }

  public static void main(String[] args) {
    System.out.println(getSum(1, 2));
    System.out.println(getSum(1, -1));
    System.out.println(getSum(2, 12345));
  }
}
