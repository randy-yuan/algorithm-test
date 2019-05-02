package randy.leetcode;

// https://leetcode-cn.com/problems/scramble-string/
// 87. 扰乱字符串
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        int len = s1.length();
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int item : count) {
            if (item != 0)
                return false;
        }

        for (int i = 1; i < len; i++) {
            String s1Left = s1.substring(0, i);
            String s1Right = s1.substring(i);
            if (isScramble(s1Left, s2.substring(0, i))
                    && isScramble(s1Right, s2.substring(i))
                    || isScramble(s1Left, s2.substring(len - i))
                    && isScramble(s1Right, s2.substring(0, len - i))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ScrambleString scrambleString = new ScrambleString();
        System.out.println(scrambleString.isScramble("great", "rgeat"));
    }
}
