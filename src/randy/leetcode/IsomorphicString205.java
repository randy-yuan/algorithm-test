package randy.leetcode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/isomorphic-strings/
// 205. 同构字符串
public class IsomorphicString205 {
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        if (s.length() == 0) return true;

        char c1, c2;
        int len = s.length();
        char[] map = new char[256];
        char[] map2 = new char[256];
        for (int i = 0; i < len; i++) {
            c1 = s.charAt(i);
            c2 = t.charAt(i);
            if (map[c1] == 0) {
                if (map2[c2] != 0) return false;
                map[c1] = c2;
                map2[c2] = c1;
            } else if (map[c1] != c2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("ab", "ca"));
    }
}
