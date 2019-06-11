package randy.leetcode;

// https://leetcode-cn.com/problems/base-7/
// 504. 七进制数
public class Base7 {
  public String convertToBase7(int num) {
    if (num == 0) return "0";
    char[] buf = new char[32];
    int idx = 31;
    int n = num > 0 ? num : -num;
    while (n != 0) {
      buf[idx--] = (char)((n % 7) + '0');
      n /= 7;
    }
    if (num < 0) buf[idx--] = '-';
    return String.valueOf(buf, idx + 1, buf.length - idx - 1);
  }
}
