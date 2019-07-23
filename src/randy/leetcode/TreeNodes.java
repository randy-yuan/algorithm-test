package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeNodes {
    public static TreeNode fromArray(Integer[] a) {
        if (a == null || a.length == 0 || a[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(a[0]);
        List<TreeNode> curNodes = new ArrayList<>();
        curNodes.add(root);

        List<TreeNode> nextNodes = new ArrayList<>();
        int idx = 1;
        while (idx < a.length) {
            int numberOfNodes = curNodes.size();
            for (int i = 0; i < numberOfNodes && idx < a.length; i++) {
                TreeNode node = curNodes.get(i);
                node.left = a[idx] != null ? new TreeNode(a[idx]) : null;
                if (++idx < a.length) {
                    node.right = a[idx] != null ? new TreeNode(a[idx]) : null;
                    idx++;
                }
                if (node.left != null) {
                    nextNodes.add(node.left);
                }
                if (node.right != null) {
                    nextNodes.add(node.right);
                }
            }

            List<TreeNode> tmp = curNodes;
            curNodes = nextNodes;
            nextNodes = tmp;
            nextNodes.clear();
        }

        return root;
    }
}
