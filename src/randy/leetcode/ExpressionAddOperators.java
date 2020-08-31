package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// 282. https://leetcode-cn.com/problems/expression-add-operators/
public class ExpressionAddOperators {
    private List<String> ans = new ArrayList<>();
    private StringBuilder expr = new StringBuilder();
    private char[] num;
    private int target;

    public List<String> addOperators(String num, int target) {
        if (num == null || num.isEmpty()) return new ArrayList<>();
        this.num = num.toCharArray();
        this.target = target;
        dfs(0, 0, 0, 0);
        return ans;
    }

    private void dfs(int idx, long op, long prev, long cur) {
        // recursion terminator
        if (idx == num.length) {
            if (op == 0 && cur == target) {
                ans.add(expr.substring(1));
            }
            return;
        }

        char c = num[idx];
        int d = c - '0';
        op = op * 10 + d;
        String opStr = String.valueOf(op);
        int opLen = opStr.length();

        // 操作数扩展1位, 不能以0开头, 需要避免: 1＋05, 1*05, etc.
        if (op > 0) {
            dfs(idx + 1, op, prev, cur);
        }

        // +
        expr.append('+').append(opStr);
        dfs(idx + 1, 0, op, cur + op);

        if (expr.length() > opLen + 1) {
            // -
            expr.setCharAt(expr.length() - opLen - 1, '-');
            dfs(idx + 1, 0, -op, cur - op);

            // *
            expr.setCharAt(expr.length() - opLen - 1, '*');
            dfs(idx + 1, 0, prev * op, cur - prev + prev * op);
        }

        // reverse expr
        expr.delete(expr.length() - opLen - 1, expr.length());
    }

    public static void main(String[] args) {
        ExpressionAddOperators addOperators = new ExpressionAddOperators();
        List<String> ans = addOperators.addOperators("12356789", 50);
        print(ans);
    }

    public static void print(List<String> ans) {
        for (String expr: ans) {
            System.out.println(expr);
        }
    }
}