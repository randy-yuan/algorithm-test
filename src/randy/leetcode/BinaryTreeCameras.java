package randy.leetcode;

import java.util.HashSet;
import java.util.Set;

// https://leetcode-cn.com/problems/binary-tree-cameras/
// 968. 监控二叉树
public class BinaryTreeCameras {
    // 动态规划
    public int minCameraCover(TreeNode root) {
        int[] res = minCameras(root);
        return Math.min(res[1], res[2]);
    }

    // res[0]: all nodes covered except root
    // res[1]: all nodes covered, the root has no camera
    // res[2]: all nodes covered, the root has camera
    private int[] minCameras(TreeNode root) {
        int[] res = {0, 0, 1000};
        if (root == null) {
            return res;
        }

        int[] left = minCameras(root.left);
        int[] right = minCameras(root.right);
        int minLeft12 = Math.min(left[1], left[2]);
        int minRight12 = Math.min(right[1], right[2]);
        res[0] = left[1] + right[1];
        res[1] = Math.min(left[2] + minRight12, right[2] + minLeft12);
        res[2] = 1 + Math.min(left[0], minLeft12) + Math.min(right[0], minRight12);

        return res;
    }

    /* 贪心法
      如果一个节点的子节点都已经被监控了，并且它还有一个父节点，那么将摄像头安放在这个节点的父节点上一定更优。
      如果一个节点存在子节点没有被摄像头监控，那么我们一定要在这个节点上放置一个摄像头。
      另外，如果一个节点没有父节点并且它还没有被监控，那么我们也要在这个节点上放置一个摄像头。
    */
    private int ans;
    Set<TreeNode> covered;
    public int minCameraCover2(TreeNode root) {
        ans = 0;
        covered = new HashSet<>();
        covered.add(null);
        dfs(root, null);
        return ans;
    }

    private void dfs(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        dfs(node.left, node);
        dfs(node.right, node);
        if (parent == null && !covered.contains(node)
                || !covered.contains(node.left)
                || !covered.contains(node.right)) {
            ans++;
            covered.add(parent);
            covered.add(node);
            covered.add(node.left);
            covered.add(node.right);
        }
    }

    public int minCameraCover3(TreeNode root) {
        if (root == null) return 0;
        if (dfs(root) == 2) ans++;
        return ans;
    }

    // 0: 节点放置了摄像头; 1: 节点已被监控, 但没有放置摄像头; 2: 节点未被监控
    private int dfs(TreeNode node) {
        if (node == null) return 1;
        int left = dfs(node.left);
        int right = dfs(node.right);

        // 存在子节点不可监控, 必需放置摄像头
        if (left == 2 || right == 2) {
            ans++;
            return 0;
        } else if (left == 0 || right == 0) {
            // 子节点已放置摄像头, 节点已被监控
            return 1;
        } else {
            return 2;
        }
    }

    public static void main(String[] args) {
        BinaryTreeCameras cameras = new BinaryTreeCameras();
        Integer[] nodes = {0,1,null,2,3,4,null,null,5};
        TreeNode root = TreeNodes.fromArray(nodes);
        System.out.println(cameras.minCameraCover(root));
    }
}
