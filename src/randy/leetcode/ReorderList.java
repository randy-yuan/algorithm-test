package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        Deque<ListNode> stack = new LinkedList<>();
        ListNode p = head;
        while (p != null) {
            stack.offerLast(p);
            p = p.next;
        }

        ListNode q = stack.pollLast();
        p = head;
        while (p != q && p.next != q) {
            q.next = p.next;
            p.next = q;
            p = q.next;
            q = stack.pollLast();
        }
        p.next = null;
    }

    public static void main(String[] args) {
        ReorderList test = new ReorderList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        test.reorderList(head);
    }
}
