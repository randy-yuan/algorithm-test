package randy.leetcode;

// https://leetcode-cn.com/problems/detect-capital/
// 520. 检测大写字母
public class DetectCapital {
  public boolean detectCapitalUse(String word) {
    int cnt = 0;
    char[] wa = word.toCharArray();
    for (char c: wa) {
      if (Character.isUpperCase(c)) cnt++;
    }
    return cnt == 0 || cnt == wa.length || cnt == 1 && Character.isUpperCase(wa[0]);
  }
}
