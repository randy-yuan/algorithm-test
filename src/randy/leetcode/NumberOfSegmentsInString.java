package randy.leetcode;

// https://leetcode-cn.com/problems/number-of-segments-in-a-string/
// 434. 字符串中的单词数
public class NumberOfSegmentsInString {
    public int countSegments(String s) {
        char[] sa = s.toCharArray();
        boolean flag = false;
        int res = 0;

        for (char c: sa) {
            if (flag) {
                if (c == ' ') {
                    res++;
                    flag = false;
                }
            } else {
                if (c != ' ') {
                    flag = true;
                }
            }
        }

        return flag ? res + 1 : res;
    }
}
