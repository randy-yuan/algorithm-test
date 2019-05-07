package randy.leetcode;

// https://leetcode-cn.com/problems/remove-outermost-parentheses/
// 1021. 删除最外层的括号
public class RemoveOutermostParentheses {
  public static String removeOuterParentheses(String S) {
    if (S.length() == 0) return S;
    StringBuilder sb = new StringBuilder();
    int[] stk = new int[S.length()];
    int top = 0;
    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) == ')') {
        if (top == 1) {
          sb.append(S, stk[0] + 1, i);
        }
        top--;
      } else {
        stk[top++] = i;
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    System.out.println(removeOuterParentheses("(()())(())"));
  }
}
