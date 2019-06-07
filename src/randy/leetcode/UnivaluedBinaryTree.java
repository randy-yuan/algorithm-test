package randy.leetcode;

// https://leetcode-cn.com/problems/univalued-binary-tree/
// 965. 单值二叉树
public class UnivaluedBinaryTree {
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        return isUnivalTree(root, root.val);
    }

    private boolean isUnivalTree(TreeNode root, int val) {
        if (root == null) return true;
        return root.val == val && isUnivalTree(root.left, val) && isUnivalTree(root.right, val);
    }
}
