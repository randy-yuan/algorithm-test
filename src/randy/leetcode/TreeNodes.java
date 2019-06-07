package randy.leetcode;

public class TreeNodes {
    public static TreeNode fromArray(Integer[] a) {
        if (a == null || a.length == 0) return null;
        return fromArray(a, 0);
    }

    private static TreeNode fromArray(Integer[] a, int idx) {
        if (idx >= a.length) return null;
        if (a[idx] == null) return null;
        TreeNode node = new TreeNode(a[idx]);
        node.left = fromArray(a, 2 * idx + 1);
        node.right = fromArray(a, 2 * idx + 2);
        return node;
    }
}
