package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/leaf-similar-trees/
// 872. 叶子相似的树
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafs1 = new ArrayList<>();
        List<Integer> leafs2 = new ArrayList<>();
        preOrder(root1, leafs1);
        preOrder(root2, leafs2);
        if (leafs1.size() != leafs2.size())
            return false;
        for (int i = 0; i < leafs1.size(); i++) {
            if (leafs1.get(i) != leafs2.get(i))
                return false;
        }
        return true;
    }

    private void preOrder(TreeNode root, List<Integer> values) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                values.add(root.val);
            } else {
                preOrder(root.left, values);
                preOrder(root.right, values);
            }
        }
    }
}
