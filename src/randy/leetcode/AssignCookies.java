package randy.leetcode;

import java.util.Arrays;

// https://leetcode-cn.com/problems/assign-cookies/
// 455. 分发饼干
public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) return 0;

        Arrays.sort(g);
        Arrays.sort(s);

        int res = 0;
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                res++;
                i++;
            }
            j++;
        }
        return res;
    }
}
