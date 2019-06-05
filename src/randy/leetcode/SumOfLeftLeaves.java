package randy.leetcode;

// https://leetcode-cn.com/problems/sum-of-left-leaves/
// 404. 左叶子之和
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                sum += sumOfLeftLeaves(root.left);
            }
        }
        if (root.right != null) {
            sum += sumOfLeftLeaves(root.right);
        }
        return sum;
    }
}
