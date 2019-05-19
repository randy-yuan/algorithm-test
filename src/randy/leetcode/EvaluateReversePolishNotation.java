package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
// 150. 逆波兰表达式求值
public class EvaluateReversePolishNotation {
  public int evalRPN(String[] tokens) {
    List<Integer> stk = new ArrayList<>();
    for (String t: tokens) {
      if ("+".equals(t)) {
        Integer a = stk.remove(stk.size() - 1);
        Integer b = stk.remove(stk.size() - 1);
        stk.add(b + a);
      } else if ("-".equals(t)) {
        Integer a = stk.remove(stk.size() - 1);
        Integer b = stk.remove(stk.size() - 1);
        stk.add(b - a);
      } else if ("*".equals(t)) {
        Integer a = stk.remove(stk.size() - 1);
        Integer b = stk.remove(stk.size() - 1);
        stk.add(b * a);
      } else if ("/".equals(t)) {
        Integer a = stk.remove(stk.size() - 1);
        Integer b = stk.remove(stk.size() - 1);
        stk.add(b / a);
      } else {
        stk.add(Integer.valueOf(t));
      }
    }
    return stk.get(0);
  }

  public static void main(String[] args) {
    EvaluateReversePolishNotation rpn = new EvaluateReversePolishNotation();
    System.out.println(rpn.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
  }
}
