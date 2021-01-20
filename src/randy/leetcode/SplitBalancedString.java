package leetcode;

public class SplitBalancedString {
    public int balancedStringSplit(String s) {
        if (s.isEmpty()) return 1;
        int count = 0;
        int sign = 0;
        for (char c: s.toCharArray()) {
            sign += (c == 'L' ? 1 : -1);
            if (sign == 0) count++;
        }
        return count;
    }
}
