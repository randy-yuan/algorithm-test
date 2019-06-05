package randy.leetcode;

// https://leetcode-cn.com/problems/remove-linked-list-elements/
// 203. 移除链表元素
public class RemoveLinkedListElements203 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;

        while (cur != null) {
            while (cur != null && cur.val == val) cur = cur.next;
            prev.next = cur;
            prev = cur;
            cur = cur != null ? cur.next : null;
        }
        return dummy.next;
    }
}
