package randy.leetcode;

// https://leetcode-cn.com/problems/add-strings/
// 415. 字符串相加
public class AddStrings {
  public String addStrings(String num1, String num2) {
    char[] na1 = num1.toCharArray();
    char[] na2 = num2.toCharArray();
    int len = Math.max(na1.length, na2.length);
    StringBuilder sb = new StringBuilder();
    int sum;
    int carry = 0;
    for (int i = 0; i < len; i++) {
      sum = carry + (i < na1.length ? na1[na1.length-i-1] - '0' : 0) +
          (i < na2.length ? na2[na2.length-i-1] - '0' : 0);
      sb.append(sum % 10);
      carry = sum / 10;
    }
    if (carry != 0) sb.append(carry);
    sb.reverse();
    return sb.toString();
  }
}
