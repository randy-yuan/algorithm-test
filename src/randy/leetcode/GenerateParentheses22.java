package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/generate-parentheses/
public class GenerateParentheses22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        helper(n, n, "", res);
        return res;
    }

    private void helper(int left, int right, String s, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) helper(left - 1, right, s + "(", res);
        if (left < right) helper(left, right - 1, s + ")", res);
    }

    public static void main(String[] args) {
        GenerateParentheses22 parentheses = new GenerateParentheses22();
        PrintUtils.print(parentheses.generateParenthesis(3));
    }
}
