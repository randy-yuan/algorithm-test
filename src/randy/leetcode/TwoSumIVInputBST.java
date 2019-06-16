package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
// 653. 两数之和 IV - 输入 BST
public class TwoSumIVInputBST {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inOrder(root, values);
        int i = 0;
        int j = values.size() - 1;
        int sum;
        while (i < j) {
            sum = values.get(i) + values.get(j);
            if (sum == k) return true;
            else if (sum < k) i++;
            else j--;
        }
        return false;
    }

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root != null) {
            inOrder(root.left, res);
            res.add(root.val);
            inOrder(root.right, res);
        }
    }
}
