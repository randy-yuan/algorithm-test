package randy.leetcode;

// https://leetcode-cn.com/problems/count-binary-substrings/
public class CountBinarySubStrings696 {
    public int countBinarySubstrings(String s) {
        int len = s.length();
        int res = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int cnt = c == '0' ? -1 : 1;
            for (int j = i + 1; j < len; j++) {
                c = s.charAt(j);
                cnt += (c == '0' ? -1 : 1);
                if (cnt == 0) {
                    res++;
                    break;
                }
            }
        }

        return res;
    }
}
