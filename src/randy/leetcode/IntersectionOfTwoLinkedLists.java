package randy.leetcode;

// https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
// 160. 相交链表
public class IntersectionOfTwoLinkedLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        int lenA = 0;
        ListNode pa = headA;
        while (pa.next != null) {
            lenA++;
            pa = pa.next;
        }

        int lenB = 0;
        ListNode pb = headB;
        while (pb.next != null) {
            lenB++;
            pb = pb.next;
        }

        if (pa != pb) return null;

        pa = headA;
        pb = headB;
        if (lenA >= lenB) {
            for (int i = 0; i < lenA - lenB; i++) pa = pa.next;
        } else {
            for (int i = 0; i < lenB - lenA; i++) pb = pb.next;
        }

        while (pa != null && pb != null && pa != pb) {
            pa = pa.next;
            pb = pb.next;
        }
        return pa;
    }
}
