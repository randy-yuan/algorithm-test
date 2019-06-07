package randy.leetcode;

// https://leetcode-cn.com/problems/quad-tree-intersection/
// 558. 四叉树交集
public class QuadTreeIntersection {
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

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1 == null || quadTree2 == null) return null;
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            return new Node(quadTree1.val || quadTree2.val, true, null, null, null, null);
        }
        if (quadTree1.isLeaf && quadTree1.val) return quadTree1;
        if (quadTree2.isLeaf && quadTree2.val) return quadTree2;

        Node topLeft = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.topLeft,
                quadTree2.isLeaf ? quadTree2 : quadTree2.topLeft);
        Node topRight = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.topRight,
                quadTree2.isLeaf ? quadTree2 : quadTree2.topRight);
        Node bottomLeft = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.bottomLeft,
                quadTree2.isLeaf ? quadTree2 : quadTree2.bottomLeft);
        Node bottomRight = intersect(quadTree1.isLeaf ? quadTree1 : quadTree1.bottomRight,
                quadTree2.isLeaf ? quadTree2 : quadTree2.bottomRight);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topLeft.val == bottomLeft.val
                && topLeft.val == bottomRight.val) {
            return topLeft;
        }
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
