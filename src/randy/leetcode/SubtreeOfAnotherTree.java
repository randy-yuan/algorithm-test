package randy.leetcode;

// https://leetcode-cn.com/problems/subtree-of-another-tree/
// 572. 另一个树的子树
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;
        if (t == null) return false;
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null) return t == null;
        if (t == null) return false;
        return s.val == t.val && isSameTree(s.left, t.left)
                && isSameTree(s.right, t.right);
    }
}
