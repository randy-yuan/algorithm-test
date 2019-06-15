package randy.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
// 501. 二叉搜索树中的众数
public class FindModeInBinarySearchTree {
  int maxCnt;
  int val;
  int cnt;

  public int[] findMode(TreeNode root) {
    if (root == null) return new int[0];

    maxCnt = 1;
    val = 0;
    cnt = 0;
    List<Integer> res = new ArrayList<>();
    findMode(root, res);
    if (cnt == maxCnt) {
      res.add(val);
    } else if (cnt > maxCnt) {
      res.clear();
      res.add(val);
    }

    int[] r = new int[res.size()];
    for (int i = 0; i < r.length; i++) {
      r[i] = res.get(i);
    }
    return r;
  }

  private void findMode(TreeNode root, List<Integer> res) {
    if (root == null) return;
    findMode(root.left, res);
    if (root.val == val) {
      cnt++;
    } else {
      if (cnt == maxCnt) {
        res.add(val);
      } else if (cnt > maxCnt) {
        maxCnt = cnt;
        res.clear();
        res.add(val);
      }
      cnt = 1;
      val = root.val;
    }
    findMode(root.right, res);
  }
}
