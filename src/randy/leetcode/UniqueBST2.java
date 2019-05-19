package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
// 95. 不同的二叉搜索树 II
public class UniqueBST2 {
    // Definition for a binary tree node.
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            for (TreeNode left: generateTrees(start, i - 1)) {
                for (TreeNode right: generateTrees(i + 1, end)) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    // 由于树本身还是要复制，所以存储已经算过的长度的树优势不大
    public List<TreeNode> generateTrees2(int n) {
        if (n == 0) return new ArrayList<>();

        List<TreeNode> r1 = new ArrayList<>();
        r1.add(new TreeNode(1));
        if (n == 1) {
            return r1;
        }

        List<TreeNode> r0 = new ArrayList<>();
        List<List<TreeNode>> dp = new ArrayList<>();
        r0.add(null);
        dp.add(r0);
        dp.add(r1);

        for (int i = 2; i <= n; i++) {
            List<TreeNode> ri = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                ri.addAll(generate(j, dp.get(j - 1), dp.get(i - j)));
            }
            dp.add(ri);
        }
        return dp.get(n);
    }

    private List<TreeNode> generate(int i, List<TreeNode> left, List<TreeNode> right) {
        List<TreeNode> result = new ArrayList<>();
        for (TreeNode r1: left) {
            for (TreeNode r2: right) {
                TreeNode r = new TreeNode(i);
                r.left = copyTree(0, r1);
                r.right = copyTree(i, r2);
                result.add(r);
            }
        }
        return result;
    }

    private TreeNode copyTree(int offset, TreeNode root) {
        if (root != null) {
            TreeNode result = new TreeNode(root.val + offset);
            result.left = copyTree(offset, root.left);
            result.right = copyTree(offset, root.right);
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        UniqueBST2 bst2 = new UniqueBST2();
        System.out.println(bst2.generateTrees(3));
    }
}
