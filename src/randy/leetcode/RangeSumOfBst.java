package randy.leetcode;

// https://leetcode-cn.com/problems/range-sum-of-bst/
// 938. 二叉搜索树的范围和
public class RangeSumOfBst {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int sum = 0;
        if (root != null) {
            if (root.val >= L && root.val <= R) {
                sum += root.val;
            }
            if (root.left != null && root.val > L) {
                sum += rangeSumBST(root.left, L, R);
            }
            if (root.right != null && root.val < R) {
                sum += rangeSumBST(root.right, L, R);
            }
        }
        return sum;
    }
}
