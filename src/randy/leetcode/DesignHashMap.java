package randy.leetcode;

// https://leetcode-cn.com/problems/design-hashmap/
// 706. 设计哈希映射
public class DesignHashMap {
    class Node {
        int key;
        int val;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }

    private static final int MAX_SIZE = 10000;

    private Node[] nodes;

    /** Initialize your data structure here. */
    public DesignHashMap() {
        nodes = new Node[MAX_SIZE];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int idx = key % nodes.length;
        Node p = nodes[idx];
        while (p != null && p.key != key) {
            p = p.next;
        }
        if (p != null) {
            p.val = value;
        } else {
            p = new Node(key, value);
            p.next = nodes[idx];
            nodes[idx] = p;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = key % nodes.length;
        Node p = nodes[idx];
        while (p != null && p.key != key) {
            p = p.next;
        }
        return p != null ? p.val : -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int idx = key % nodes.length;
        Node prev = null;
        Node p = nodes[idx];
        while (p != null && p.key != key) {
            prev = p;
            p = p.next;
        }
        if (p != null) {
            if (prev != null) {
                prev.next = p.next;
            } else {
                nodes[idx] = p.next;
            }
        }
    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
