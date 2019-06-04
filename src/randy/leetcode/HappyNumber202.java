package randy.leetcode;

// https://leetcode-cn.com/problems/happy-number/
// 202. 快乐数
public class HappyNumber202 {
  public static boolean isHappy(int n) {
    if (n <= 0) return false;

    int sum = n;
    int x;
    int y;
    do {
      x = sum;
      sum = 0;
      while (x != 0) {
        y = x % 10;
        x /= 10;
        sum += y * y;
      }
    } while (sum != 1 && sum != n && sum != 4);

    return sum == 1;
  }

  public static void main(String[] args) {
    System.out.println(isHappy(2));
    System.out.println(isHappy(4));
  }
}
