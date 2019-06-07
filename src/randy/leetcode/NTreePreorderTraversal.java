package randy.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static randy.leetcode.NTreeLevelOrderTraversal.Node;

// https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
// 589. N叉树的前序遍历
public class NTreePreorderTraversal {
    // 迭代法
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Deque<Node> stk = new LinkedList<>();
        stk.push(root);
        while (!stk.isEmpty()) {
            Node node = stk.pop();
            res.add(node.val);
            if (node.children != null) {
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    stk.push(node.children.get(i));
                }
            }
        }

        return res;
    }

    // 递归
    public List<Integer> preorder2(Node root) {
        List<Integer> res = new ArrayList<>();
        preorder2(root, res);
        return res;
    }

    private void preorder2(Node root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        if (root.children != null) {
            for (Node c: root.children) {
                preorder2(c, res);
            }
        }
    }
}
