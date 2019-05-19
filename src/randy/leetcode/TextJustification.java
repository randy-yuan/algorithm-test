package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/text-justification/
// 68. 文本左右对齐
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int wi = 0;
        int len = 0;
        int remain;
        List<String> line = new ArrayList<>();
        for (; wi < words.length; wi++) {
            remain = maxWidth - len - 1;
            if (len == 0) {
                len += words[wi].length();
                line.add(words[wi]);
            } else if (words[wi].length() <= remain) {
                len += 1 + words[wi].length();
                line.add(" ");
                line.add(words[wi]);
            } else {
                adjust(line, remain + 1);
                result.add(String.join("", line));
                len = 0;
                line.clear();
                wi--;
            }
        }

        if (len > 0) {
            for (; len < maxWidth; len++) line.add(" ");
            result.add(String.join("", line));
        }

        return result;
    }

    private void adjust(List<String> line, int remain) {
        if (remain == 0) return;
        if (line.size() == 1) {
            line.add(nSpace(remain));
            return;
        }

        int count = line.size() / 2;
        int avg = remain / count;
        int end = remain - avg * count;
        for (int i = 0; i < count; i++) {
            if (i < end) {
                line.set(2 * i + 1, nSpace(avg + 2));
            } else {
                line.set(2 * i + 1, nSpace(avg + 1));
            }
        }
    }

    private String nSpace(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();

        int start = 0;
        int end;
        int len = 0;
        int remain = 0;
        for (int i = 0; i < words.length; i++) {
            remain = maxWidth - len - 1;
            if (len == 0) {
                start = i;
                len += words[i].length();
            } else if (words[i].length() <= remain) {
                len += 1 + words[i].length();
            } else {
                end = i;
                result.add(adjustLine(words, start, end, remain + 1));
                len = 0;
                i--;
            }
        }

        result.add(adjustLine(words, start, words.length, maxWidth - len));

        return result;
    }

    private String adjustLine(String[] words, int start, int end, int remain) {
        int wc = end - start;
        StringBuilder sb = new StringBuilder();
        sb.append(words[start]);
        if (wc == 1) {
            for (int j = 0; j < remain; j++) {
                sb.append(' ');
            }
        } else if (end == words.length) {
            for (int i = start + 1; i < end; i++) {
                sb.append(' ').append(words[i]);
            }
            for (int j = 0; j < remain; j++) {
                sb.append(' ');
            }
        } else {
            int avg = 1 + remain / (wc - 1);
            int extra = remain % (wc - 1);
            for (int i = start + 1; i < end; i++) {
                for (int j = 0; j < avg; j++) {
                    sb.append(' ');
                }
                if (extra > 0) {
                    sb.append(' ');
                    extra--;
                }
                sb.append(words[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TextJustification tj = new TextJustification();

        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        PrintUtils.print(tj.fullJustify2(words, 16));

        String[] words2 = {"What","must","be","acknowledgment","shall","be"};
        PrintUtils.print(tj.fullJustify2(words2, 16));
    }
}
