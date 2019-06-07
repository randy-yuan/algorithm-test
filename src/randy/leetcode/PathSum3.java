package randy.leetcode;

// https://leetcode-cn.com/problems/path-sum-iii/
// 437. 路径总和 III
public class PathSum3 {
    // 递归注意避免重复计算
    public int pathSum(TreeNode root, int sum) {
        return pathSum(root, sum, false);
    }

    private int pathSum(TreeNode root, int sum, boolean flag) {
        if (root == null) return 0;
        int res = 0;
        if (root.val == sum) res++;
        res += pathSum(root.left, sum - root.val, true);
        res += pathSum(root.right, sum - root.val, true);
        if (!flag) {
            res += pathSum(root.left, sum, false);
            res += pathSum(root.right, sum, false);
        }
        return res;
    }

    public static void main(String[] args) {
        Integer[] nodes = {10,5,-3,3,2,null,11,3,-2,null,1};
        TreeNode root = TreeNodes.fromArray(nodes);
        PathSum3 pathSum3 = new PathSum3();
        System.out.println(pathSum3.pathSum(root, 8));
    }
}
