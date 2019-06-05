package randy.leetcode;

// https://leetcode-cn.com/problems/reverse-linked-list/
// 206. 反转链表
public class ReverseLinkedList206 {
    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 迭代
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // 递归
    public ListNode reverseList2(ListNode head) {
        if (head == null) return null;
        ListNode p = head;
        while (p.next != null) p = p.next;
        reverseTail(head);
        return p;
    }

    // 递归，返回tail
    private ListNode reverseTail(ListNode head) {
        if (head == null) return null;
        ListNode prev = reverseTail(head.next);
        if (prev != null) prev.next = head;
        head.next = null;
        return head;
    }
}
