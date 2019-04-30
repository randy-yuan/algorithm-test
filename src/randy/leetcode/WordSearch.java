package randy.leetcode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/word-search/
// 79. 单词搜索
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        int m = board.length;
        int n = board[0].length;
        boolean[] flag = new boolean[m * n];

        Set<Integer> matched = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //if (exist(board, -1, i * n + j, word, 0, matched)) return true;
                if (exist2(board, flag, i * n + j, word, 0)) return true;
            }
        }
        return false;
    }

    public static boolean exist(char[][] board, int prevIdx, int idx, String word, int wordIdx, Set<Integer> matched) {
        int m = board.length;
        int n = board[0].length;
        int r = idx / n;
        int c = idx % n;

        if (board[r][c] != word.charAt(wordIdx)) return false;
        if (matched.contains(idx)) return false;
        if (wordIdx == word.length() - 1) return true;

        matched.add(idx);
        int nextIdx = idx - n;
        if (r > 0 && nextIdx != prevIdx && exist(board, idx, nextIdx, word, wordIdx + 1, matched)) return true;

        nextIdx = idx + n;
        if (r < (m - 1) && nextIdx != prevIdx && exist(board, idx, nextIdx, word, wordIdx + 1, matched)) return true;

        nextIdx = idx - 1;
        if (c > 0 && nextIdx != prevIdx && exist(board, idx, nextIdx, word, wordIdx + 1, matched)) return true;

        nextIdx = idx + 1;
        if (c < (n - 1) && nextIdx != prevIdx && exist(board, idx, nextIdx, word, wordIdx + 1, matched)) return true;

        matched.remove(idx);
        return false;
    }

    public static boolean exist2(char[][] board, boolean[] flag, int idx, String word, int wordIdx) {
        int m = board.length;
        int n = board[0].length;
        int r = idx / n;
        int c = idx % n;

        if (flag[idx]) return false;
        if (board[r][c] != word.charAt(wordIdx)) return false;
        if (wordIdx++ == word.length() - 1) return true;

        flag[idx] = true;

        if (r > 0 && exist2(board, flag, idx - n, word, wordIdx)
            || r < (m - 1) && exist2(board, flag, idx + n, word, wordIdx)
            || c > 0 && exist2(board, flag, idx - 1, word, wordIdx)
            || c < (n - 1) && exist2(board, flag, idx + 1, word, wordIdx)) return true;

        return flag[idx] = false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};

        System.out.println(exist(board, "ABCCED"));
    }
}
