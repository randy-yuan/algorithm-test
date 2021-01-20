package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 301 https://leetcode-cn.com/problems/remove-invalid-parentheses/
public class RemoveInvalidParentheses {
    private Set<String> res;

    public List<String> removeInvalidParentheses(String s) {
        res = new HashSet<>();
        char[] arr = s.toCharArray();

        int left = 0;
        int right = 0;
        for (char c: arr) {
            if (c == '(') left++;
            else if (c == ')') {
                if (left == 0) right++;
                else left--;
            }
        }

        helper(s.toCharArray(), 0, 0, 0, left, right, "");
        return new ArrayList<>(res);
    }

    private void helper(char[] arr, int idx, int leftCount, int rightCount, int leftRem, int rightRem, String ans) {
        if (idx == arr.length) {
            if (leftCount == rightCount) res.add(ans);
            return;
        }

        if (arr[idx] == '(') {
            helper(arr, idx + 1, leftCount + 1,  rightCount, leftRem, rightRem, ans + arr[idx]);
            if (leftRem > 0) helper(arr, idx + 1, leftCount, rightCount, leftRem - 1, rightRem, ans);
        } else if (arr[idx] == ')') {
            if (rightCount < leftCount) helper(arr, idx + 1, leftCount, rightCount + 1, leftRem, rightRem, ans + arr[idx]);
            if (rightRem > 0) helper(arr, idx + 1, leftCount, rightCount, leftRem, rightRem - 1, ans);
        } else {
            helper(arr, idx + 1, leftCount, rightCount, leftRem, rightRem, ans + arr[idx]);
        }
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        PrintUtils.print(removeInvalidParentheses.removeInvalidParentheses("()())()"));
    }
}
