package randy.leetcode;

// https://leetcode-cn.com/problems/first-unique-character-in-a-string/
// 387. 字符串中的第一个唯一字符
public class FirstUniqueCharacter {
    public int firstUniqChar(String s) {
        if (s.length() == 0) return -1;
        int[] count = new int[26];
        char[] sa = s.toCharArray();
        for (char c: sa) {
            count[c - 'a']++;
        }
        for (int i = 0; i < sa.length; i++) {
            if (count[sa[i] - 'a'] == 1) return i;
        }
        return -1;
    }
}
