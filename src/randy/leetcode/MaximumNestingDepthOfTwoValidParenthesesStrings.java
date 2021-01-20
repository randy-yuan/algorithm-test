package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class MaximumNestingDepthOfTwoValidParenthesesStrings {
    public int[] maxDepthAfterSplit(String seq) {
        int[] r = new int[seq.length()];
        Deque<Integer> stk = new ArrayDeque<>();
        char[] arr = seq.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                if (stk.peekFirst() == null) {
                    r[i] = 0;
                } else {
                    r[i] = 1 - stk.peekFirst();
                }
                stk.offerFirst(r[i]);
            } else {
                r[i] = stk.pollFirst();
            }
        }

        return r;
    }

    public static void main(String[] args) {
        MaximumNestingDepthOfTwoValidParenthesesStrings test = new MaximumNestingDepthOfTwoValidParenthesesStrings();
        System.out.println(test.maxDepthAfterSplit("(()())").toString());
    }
}
