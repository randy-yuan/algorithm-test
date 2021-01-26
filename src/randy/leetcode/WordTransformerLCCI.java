package randy.leetcode;

import java.util.*;

public class WordTransformerLCCI {
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> res = new ArrayList<>();
        if (beginWord.length() != endWord.length()) return res;
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return res;
        words.add(beginWord);
        backtrack(beginWord.toCharArray(), endWord, new HashSet<>(), words, res);
        return res;
    }

    private boolean backtrack(char[] bw, String ew, Set<String> visited, Set<String> words, List<String> res) {
        String w = new String(bw);
        if (w.equals(ew)) {
            res.add(ew);
            return true;
        }

        if (visited.contains(w)) return false;
        if (!words.contains(w)) return false;
        visited.add(w);
        res.add(w);

        for (int i = 0; i < bw.length; i++) {
            char c = bw[i];
            for (int j = 0; j < 26; j++) {
                bw[i] = (char)('a' + j);
                if (backtrack(bw, ew, visited, words, res)) return true;
            }
            bw[i] = c;
        }

        visited.remove(w);
        res.remove(res.size()-1);
        return false;
    }

    public static void main(String[] args) {
        WordTransformerLCCI test = new WordTransformerLCCI();
        PrintUtils.print(test.findLadders("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog")));
    }
}
