package randy.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode-cn.com/problems/basic-calculator/
/*
实现一个基本的计算器来计算一个简单的字符串表达式的值。

字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 */
public class BasicCalculator224 {
    public static int calculate(String s) {
        Deque<Boolean> op = new LinkedList<>();
        Boolean isPositive = true;
        Boolean lastPositive = true;
        int res = 0;

        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int n = c - '0';
                for (i++; i < length; i++) {
                    c = s.charAt(i);
                    if (Character.isDigit(c)) {
                        n = n * 10 + c - '0';
                    } else {
                        i--;
                        break;
                    }
                }
                if (isPositive && lastPositive || !isPositive && !lastPositive) {
                    res += n;
                } else {
                    res -= n;
                }
            } else if (c == '(') {
                op.offer(isPositive);
                isPositive = isPositive &&lastPositive || !isPositive && !lastPositive;
                lastPositive = true;
            } else if (c == ')') {
                lastPositive = isPositive;
                isPositive = op.removeLast();
            } else if (c == '+') {
                lastPositive = true;
            } else if (c == '-') {
                lastPositive = false;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculate("2-4-(8+2-6+(8+4-(1)+8-10))"));
    }
}
