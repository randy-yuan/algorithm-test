package randy.leetcode;

// https://leetcode-cn.com/problems/cousins-in-binary-tree/
// 993. 二叉树的堂兄弟节点
public class CousinsInBinaryTree {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;
        int xi = dfs(root, 0, x);
        int yi = dfs(root, 0, y);
        if (Math.abs(xi - yi) == 1) return false;
        int xh = height(xi);
        int yh = height(yi);
        return xh == yh;
    }

    private int dfs(TreeNode root, int idx, int val) {
        if (root == null) return -1;
        if (root.val == val) return idx;
        int res = dfs(root.left, 2 * idx + 1, val);
        if (res != -1) return res;
        return dfs(root.right, 2 * idx + 2, val);
    }

    private int height(int idx) {
        int h = 0;
        while (idx != 0) {
            idx = (idx - 1) >> 1;
            h++;
        }
        return h;
    }

    public static void main(String[] args) {
        Integer[] nodes = {1,2,3,null,4,null,5};
        TreeNode root = TreeNodes.fromArray(nodes);
        CousinsInBinaryTree cousins = new CousinsInBinaryTree();
        System.out.println(cousins.isCousins(root, 5, 4));
    }
}
