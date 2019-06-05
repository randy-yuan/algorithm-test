package randy.leetcode;

// https://leetcode-cn.com/problems/find-the-difference/
// 389. 找不同
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        for (char c: sa) {
            count[c - 'a']++;
        }
        for (char c: ta) {
            if (--count[c - 'a'] < 0) return c;
        }
        return ta[0];
    }

    // 利用异或运算的特性，找出出现次数为奇数的
    public char findTheDifference2(String s, String t) {
        char ans = 0;
        for (char c : s.toCharArray()) {
            ans ^= c;
        }
        for (char c : t.toCharArray()) {
            ans ^= c;
        }
        return ans;
    }
}
