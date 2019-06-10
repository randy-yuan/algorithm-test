package randy.leetcode;

// https://leetcode-cn.com/problems/construct-quad-tree/
// 427. 建立四叉树
public class ConstructQuadTree {
  // Definition for a QuadTree node.
  class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
      val = _val;
      isLeaf = _isLeaf;
      topLeft = _topLeft;
      topRight = _topRight;
      bottomLeft = _bottomLeft;
      bottomRight = _bottomRight;
    }
  }

  public Node construct(int[][] grid) {
    if (grid.length == 0) return null;
    return construct(grid, 0, 0, grid.length);
  }

  private Node construct(int[][] grid, int r, int c, int n) {
    if (n == 1) {
      return new Node(grid[r][c] == 1, true, null, null, null, null);
    }

    int m = n >> 1;
    Node topLeft = construct(grid, r, c, m);
    Node topRight = construct(grid, r, c + m, m);
    Node bottomLeft = construct(grid, r + m, c, m);
    Node bottomRight = construct(grid, r + m, c + m , m);
    if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
        && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
      return topLeft;
    }

    return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
  }

  public static void main(String[] args) {
    int[][] grid = {{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},
        {1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0},{1,1,1,1,0,0,0,0}};
    ConstructQuadTree constructQuadTree = new ConstructQuadTree();
    constructQuadTree.construct(grid);
  }
}
