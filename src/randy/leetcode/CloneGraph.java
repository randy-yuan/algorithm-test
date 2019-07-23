package randy.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** https://leetcode-cn.com/problems/clone-graph/
 * 133. 克隆图
 * @see CopyListWithRandomPointer
 */
public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Map<Node, Node> nodes = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null)
            return null;

        Node clone = nodes.get(node);
        if (clone != null)
            return clone;

        clone = new Node(node.val, new ArrayList<>());
        nodes.put(node, clone);
        for (Node n: node.neighbors) {
            clone.neighbors.add(cloneGraph(n));
        }
        return clone;
    }
}
