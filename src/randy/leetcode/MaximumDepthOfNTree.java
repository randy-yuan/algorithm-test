package randy.leetcode;
import static randy.leetcode.NTreeLevelOrderTraversal.Node;

// https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
// 559. N叉树的最大深度
public class MaximumDepthOfNTree {
    public int maxDepth(Node root) {
        return maxDepth(root, 1);
    }

    private int maxDepth(Node root, int depth) {
        if (root == null) return depth - 1;
        int maxDepth = depth++;
        if (root.children != null) {
            for (Node c: root.children) {
                maxDepth = Math.max(maxDepth, maxDepth(c, depth));
            }
        }
        return maxDepth;
    }
}
