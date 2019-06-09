package randy.leetcode;

// https://leetcode-cn.com/problems/license-key-formatting/
// 482. 密钥格式化
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String S, int K) {
        char[] sa = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = sa.length - 1; i >= 0; i--) {
            if (sa[i] == '-') continue;
            if (cnt == K) {
                cnt = 1;
                sb.append('-');
                sb.append(Character.toUpperCase(sa[i]));
            } else {
                sb.append(Character.toUpperCase(sa[i]));
                cnt++;
            }
        }
        return sb.reverse().toString();
    }
}
