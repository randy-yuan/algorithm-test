package randy.leetcode;

// https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree/
// 671. 二叉树中第二小的节点
public class SecondMinimumNodeInBinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) return -1;
        int left = root.left.val != root.val ? root.left.val : findSecondMinimumValue(root.left);
        int right = root.right.val != root.val ? root.right.val : findSecondMinimumValue(root.right);
        if (left != -1 && right != -1) return Math.min(left, right);
        return left != -1 ? left : right;
    }
}
