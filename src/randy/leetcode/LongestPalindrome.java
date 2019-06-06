package randy.leetcode;

// https://leetcode-cn.com/problems/longest-palindrome/
// 409. 最长回文串
public class LongestPalindrome {
  public int longestPalindrome(String s) {
    if (s.length() <= 1) return s.length();
    int[] count = new int[128];
    for (char c: s.toCharArray()) {
      count[c]++;
    }

    int odd = 0;
    int even = 0;
    for (int i = 0; i < 128; i++) {
      odd += count[i] & 1;
      even += (count[i] >> 1) << 1;
    }
    return even + (odd > 0 ? 1 : 0);
  }
}
