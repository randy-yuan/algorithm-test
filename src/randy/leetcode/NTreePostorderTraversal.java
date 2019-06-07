package randy.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static randy.leetcode.NTreeLevelOrderTraversal.Node;

/** https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 590. N叉树的后序遍历

 @see NTreePreorderTraversal   前序遍历, 先根后子
 @see NTreePostorderTraversal  后序遍历, 先子后根
 @see NTreeLevelOrderTraversal 层次遍历, 从上到下，逐层访问

 N叉树没有中序遍历，二叉树才有
 */
public class NTreePostorderTraversal {
    // 迭代法
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<Node> stk = new LinkedList<>();
        Deque<Boolean> flags = new LinkedList<>();
        stk.push(root);
        flags.push(false);
        while (!stk.isEmpty()) {
            Boolean flag = flags.pop();
            if (flag) {
                res.add(stk.pop().val);
            } else {
                flags.push(true);
                Node node = stk.peek();
                if (node.children != null) {
                    for (int i = node.children.size() - 1; i >= 0; i--) {
                        stk.push(node.children.get(i));
                        flags.push(false);
                    }
                }
            }
        }

        return res;
    }

    // 递归
    public List<Integer> postorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        postorder2(root, res);
        return res;
    }

    private void postorder2(Node root, List<Integer> res) {
        if (root == null) return;
        if (root.children != null) {
            for (Node c: root.children) {
                postorder2(c, res);
            }
        }
        res.add(root.val);
    }
}
