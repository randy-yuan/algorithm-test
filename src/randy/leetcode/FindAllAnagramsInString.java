package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
// 438. 找到字符串中所有字母异位词
public class FindAllAnagramsInString {
    public static List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[26];
        int len = p.length();
        for (char c: p.toCharArray()) {
            map[c - 'a']++;
        }

        List<Integer> res = new ArrayList<>();
        char[] sa = s.toCharArray();
        if (sa.length == 0 || sa.length < len) return res;
        int[] smap = new int[26];
        int i = 0;
        int j = 0;
        int k;
        for (; j < len - 1; j++) {
            smap[sa[j] - 'a']++;
        }
        for (; j < sa.length; i++, j++) {
            smap[sa[j] - 'a']++;
            for (k = 0; k < 26; k++) {
                if (smap[k] != map[k]) break;
            }
            if (k == 26) res.add(i);
            smap[sa[i] - 'a']--;
        }
        return res;
    }

    public static List<Integer> findAnagrams2(String s, String p) {
        int[] map = new int[26];
        int len = p.length();
        for (char c: p.toCharArray()) {
            map[c - 'a']++;
        }

        List<Integer> res = new ArrayList<>();
        char[] sa = s.toCharArray();
        if (sa.length == 0 || sa.length < len) return res;

        int i = 0;
        int j = 0;
        int cnt = len;
        for (; j < sa.length; j++) {
            if (map[sa[j] - 'a']-- > 0) cnt--;
            if (cnt == 0) res.add(i);
            if ((j - i + 1) == len && map[sa[i++] - 'a']++ >= 0) cnt++;
        }
        return res;
    }

    public static void main(String[] args) {
        PrintUtils.print(findAnagrams2("cbaebabacd", "abc"));
    }
}
