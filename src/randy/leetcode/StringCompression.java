package randy.leetcode;

// https://leetcode-cn.com/problems/string-compression/
// 443. 压缩字符串
public class StringCompression {
    public int compress(char[] chars) {
        int pos = 0;
        int cnt = 1;
        char prev = chars[0];
        for (int i = 1; i <= chars.length; i++) {
            if (i < chars.length && chars[i] == prev) cnt++;
            else {
                chars[pos++] = prev;
                if (i < chars.length) prev = chars[i];
                if (cnt > 1) {
                    for (char c: String.valueOf(cnt).toCharArray()) {
                        chars[pos++] = c;
                    }
                    cnt = 1;
                }
            }
        }
        return pos;
    }
}
