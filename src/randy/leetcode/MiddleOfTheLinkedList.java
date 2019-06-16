package randy.leetcode;

// https://leetcode-cn.com/problems/middle-of-the-linked-list/
// 876. 链表的中间结点
public class MiddleOfTheLinkedList {
    public ListNode middleNode(ListNode head) {
        int cnt = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            cnt++;
        }

        int mid = cnt / 2;
        p = head;
        for (int i = 0; i < mid; i++) {
            p = p.next;
        }
        return p;
    }
}
