package randy.leetcode;

// https://leetcode-cn.com/problems/longest-uncommon-subsequence-i/
// 521. 最长特殊序列 Ⅰ
public class LongestUnCommonSubSequence1 {
  public int findLUSlength(String a, String b) {
    if (a.equals(b)) return -1;
    return Math.max(a.length(), b.length());
  }
}
