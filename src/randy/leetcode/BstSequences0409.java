package randy.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode-cn.com/problems/bst-sequences-lcci/
public class BstSequences0409 {
    public List<List<Integer>> BSTSequences(TreeNode root) {
        Set<TreeNode> nodes = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            nodes.add(root);
        }
        dfs(nodes, path, res);
        return res;
    }

    private void dfs(Set<TreeNode> nodes, List<Integer> path, List<List<Integer>> res) {
        if (nodes.isEmpty()) {
            res.add(new ArrayList<>(path));
            return;
        }

        Set<TreeNode> nextNodes = new HashSet<>(nodes);
        for (TreeNode n: nodes) {
            path.add(n.val);

            nextNodes.remove(n);
            if (n.left != null) nextNodes.add(n.left);
            if (n.right != null) nextNodes.add(n.right);

            dfs(nextNodes, path, res);

            nextNodes.add(n);
            if (n.left != null) nextNodes.remove(n.left);
            if (n.right != null) nextNodes.remove(n.right);

            path.remove(path.size()-1);
        }
    }
}
