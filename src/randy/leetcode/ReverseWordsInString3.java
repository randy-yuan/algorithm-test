package randy.leetcode;

// https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
// 557. 反转字符串中的单词 III
public class ReverseWordsInString3 {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder wb = new StringBuilder();
        boolean inWord = false;
        char[] sa = s.toCharArray();
        for (char c: sa) {
            if (c == ' ') {
                if (inWord) {
                    sb.append(wb.reverse());
                    wb.setLength(0);
                    inWord = false;
                }
                sb.append(c);
            } else {
                wb.append(c);
                inWord = true;
            }
        }
        if (wb.length() > 0) {
            sb.append(wb.reverse());
        }
        return sb.toString();
    }

    public String reverseWords2(String s) {
        char[] sa = s.toCharArray();
        int start = 0;
        int end = 0;
        while (start < sa.length) {
            end = s.indexOf(' ', start);
            if (end == -1) end = sa.length;
            reverse(sa, start, end);
            start = end + 1;
        }
        return String.valueOf(sa);
    }

    private void reverse(char[] sa, int start, int end) {
        int i = start;
        int j = end - 1;
        while (i < j) {
            char c = sa[i];
            sa[i++] = sa[j];
            sa[j--] = c;
        }
    }
}
