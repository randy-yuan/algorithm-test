package randy.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
// 429. N叉树的层序遍历
public class NTreeLevelOrderTraversal {
    // Definition for a Node.
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> level = new ArrayList<>();
        Deque<Node> stk = new LinkedList<>();
        Node dummy = new Node();
        stk.addLast(root);
        stk.addLast(dummy);
        while (!stk.isEmpty()) {
            Node n = stk.removeFirst();
            if (n == dummy) {
                res.add(level);
                if (!stk.isEmpty()) {
                    level = new ArrayList<>();
                    stk.addLast(dummy);
                }
            } else {
                level.add(n.val);
                for (Node c: n.children) {
                    stk.addLast(c);
                }
            }
        }
        return res;
    }

    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrder2(root, 0, res);
        return res;
    }

    private void levelOrder2(Node root, int level, List<List<Integer>> res) {
        if (root != null) {
            List<Integer> levelRes;
            if (level < res.size()) {
                levelRes = res.get(level);
            } else {
                levelRes = new ArrayList<>();
                res.add(levelRes);
            }
            levelRes.add(root.val);
            if (root.children != null) {
                for (Node c: root.children) {
                    levelOrder2(c, level + 1, res);
                }
            }
        }
    }
}
