package randy.leetcode;

// https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
// 530. 二叉搜索树的最小绝对差
public class MinimumAbsoluteDifferenceInBST {
  long val = Long.MAX_VALUE;
  long maxDiff = Integer.MAX_VALUE;

  // 中序遍历
  public int getMinimumDifference(TreeNode root) {
    if (root != null) {
      getMinimumDifference(root.left);
      maxDiff = Math.min(maxDiff, Math.abs(root.val - val));
      val = root.val;
      getMinimumDifference(root.right);
    }
    return (int)maxDiff;
  }
}
