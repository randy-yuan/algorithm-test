package randy.leetcode;

// https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
// 783. 二叉搜索树结点最小距离
public class MinmumDistanceBetweenBSTNodes {
    private TreeNode prev = null;

    public int minDiffInBST(TreeNode root) {
        return inOrder(root);
    }

    private int inOrder(TreeNode root) {
        int res = Integer.MAX_VALUE;
        if (root != null) {
            res = Math.min(res, inOrder(root.left));
            if (prev != null) {
                res = Math.min(res, root.val - prev.val);
            }
            prev = root;
            res = Math.min(res, inOrder(root.right));
        }
        return res;
    }
}
