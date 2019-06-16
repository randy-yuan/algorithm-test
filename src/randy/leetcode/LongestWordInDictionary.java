package randy.leetcode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/longest-word-in-dictionary/
// 720. 词典中最长的单词
public class LongestWordInDictionary {
    public String longestWord(String[] words) {
        if (words.length == 0) return "";
        Set<String> set = new HashSet<>();
        for (String s: words)
            set.add(s);

        int idx = -1;
        for (int i = 0; i < words.length; i++) {
            if (idx != -1) {
                if (words[i].length() < words[idx].length())
                    continue;
                if (words[i].length() == words[idx].length()
                        && words[i].compareTo(words[idx]) >= 0)
                    continue;
            }
            boolean flag = true;
            int len = words[i].length();
            for (int j = 1; j < len; j++) {
                if (!set.contains(words[i].substring(0, j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                idx = i;
            }
        }
        return idx != -1 ? words[idx] : "";
    }

    public static void main(String[] args) {
        LongestWordInDictionary longestWord = new LongestWordInDictionary();
        String[] words = {"ts","e","x","pbhj","opto","xhigy","erikz","pbh",
                "opt","erikzb","eri","erik","xlye","xhig","optoj","optoje",
                "xly","pb","xhi","x","o"};
        System.out.println(longestWord.longestWord(words));
    }
}
