package randy.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/valid-anagram/
// 242. 有效的字母异位词
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        for (int i = 0; i < sa.length; i++) {
            if (sa[i] != ta[i]) return false;
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0) return false;
        }
        return true;
    }
}
