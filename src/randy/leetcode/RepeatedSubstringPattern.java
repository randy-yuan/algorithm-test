package randy.leetcode;

// https://leetcode-cn.com/problems/repeated-substring-pattern/
// 459. 重复的子字符串
public class RepeatedSubstringPattern {
    // 从长度1到s.length()/2逐个测试
    public static boolean repeatedSubstringPattern(String s) {
        char[] sa = s.toCharArray();
        if (sa.length <= 1) return false;
        int n = sa.length / 2;
        for (int i = 1; i <= n; i++) {
            if ((sa.length % i) != 0) continue;
            int j = 0;
            int k = j + i;
            for (; k < sa.length && sa[j] == sa[k]; j++, k++);
            if (k == sa.length) return true;
        }
        return false;
    }

    // 通过正则测试
    public static boolean repeatedSubstringPattern2(String s) {
        return s.matches("(\\w+)\\1+");
    }

    // 假设母串s中子串s'重复n次, 则s+s中子串s'重复2n次，
    // 现在s=ns', s+s=2ns', 因此s在(s+s)[1:-1]中必出现一次以上
    // 实际测试，居然还没有逐个测试快，看来是indexOf函数比较慢
    public static boolean repeatedSubstringPattern3(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s, 1, s.length());
        sb.append(s, 0, s.length() - 1);
        return sb.indexOf(s) != -1;
    }

    public static void main(String[] args) {
        System.out.println(repeatedSubstringPattern("a"));
        System.out.println(repeatedSubstringPattern("aa"));
        System.out.println(repeatedSubstringPattern("aba"));
        System.out.println(repeatedSubstringPattern2("abcabcabc"));
    }
}
