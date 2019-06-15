package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/keyboard-row/
// 500. 键盘行
public class KeyboardRow {
  public String[] findWords(String[] words) {
    String[] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
    int[] flag = new int[26];
    for (int i = 0; i < keyboard.length; i++) {
      for (char c: keyboard[i].toCharArray()) {
        flag[c-'a'] = i;
      }
    }

    List<String> res = new ArrayList<>();
    for (String w: words) {
      int r = flag[Character.toLowerCase(w.charAt(0)) - 'a'];
      boolean found = false;
      for (char c: w.toCharArray()) {
        if (flag[Character.toLowerCase(c)-'a'] != r) {
          found = true;
          break;
        }
      }
      if (!found) res.add(w);
    }

    String[] sa = new String[res.size()];
    return res.toArray(sa);
  }
}
