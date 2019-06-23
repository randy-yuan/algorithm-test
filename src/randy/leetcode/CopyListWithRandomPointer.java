package randy.leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/copy-list-with-random-pointer/
// 138. 复制带随机指针的链表
// https://leetcode-cn.com/problems/copy-list-with-random-pointer/solution/fu-zhi-dai-sui-ji-zhi-zhen-de-lian-biao-by-leetcod/
public class CopyListWithRandomPointer {
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> nodes = new HashMap<>();
        Node dummy = new Node();
        Node prev = dummy;
        Node p = head;
        Node copy;
        while (p != null) {
            copy = new Node(p.val, p.next, p.random);
            nodes.put(p, copy);
            prev.next = copy;
            prev = copy;
            p = p.next;
        }

        p = dummy.next;
        while (p != null) {
            p.random = nodes.get(p.random);
            p = p.next;
        }

        return dummy.next;
    }

    // 回溯
    public Node copyRandomList2(Node head) {
        Map<Node, Node> visited = new HashMap<>();
        return copyNode(head, visited);
    }

    private Node copyNode(Node head, Map<Node, Node> visited) {
        if (head == null) return null;

        Node copy = visited.get(head);
        if (copy != null) return copy;

        copy = new Node(head.val, null, null);
        visited.put(head, copy);
        copy.next = copyNode(head.next, visited);
        copy.random = copyNode(head.random, visited);
        return copy;
    }

    public Node copyRandomList3(Node head) {
        if (head == null) return null;
        Map<Node, Node> visited = new HashMap<>();
        Node newHead = new Node(head.val, null, null);
        Node copy = newHead;
        Node p = head;
        visited.put(p, copy);
        while (p != null) {
            if (p.next != null) {
                copy.next = visited.computeIfAbsent(p.next, next -> new Node(next.val, null, null));
            }
            if (p.random != null) {
                copy.random = visited.computeIfAbsent(p.random, random -> new Node(random.val, null, null));
            }
            p = p.next;
            copy = copy.next;
        }
        return newHead;
    }

    // O(1) 空间迭代, 最佳解法
    public Node copyRandomList4(Node head) {
        if (head == null) return null;
        Node p = head;
        while (p != null) {
            Node q = new Node(p.val, p.next, null);
            p.next = q;
            p = q.next;
        }

        p = head;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }

        Node neaHead = head.next;
        p = head;
        while (p != null) {
            Node q = p.next;
            p.next = q.next;
            p = p.next;
            q.next = p != null ? p.next : null;
        }

        return neaHead;
    }
}
