package leetcode;

//
public class SortList148 {
    public ListNode sortList(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }

        return sort(head, len);
    }

    private ListNode sort(ListNode head, int len) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (int i = 1; i < len; i *= 2) {
            ListNode cur = dummy.next;
            ListNode pre = dummy;

            while (cur != null) {
                ListNode p1 = cur;
                ListNode p2 = cut(cur, i);
                cur = cut(p2, i);

                while (p1 != null && p2 != null) {
                    if (p1.val <= p2.val) {
                        pre.next = p1;
                        pre = pre.next;
                        p1 = p1.next;
                    } else {
                        pre.next = p2;
                        pre = pre.next;
                        p2 = p2.next;
                    }
                }

                pre.next = (p1 != null) ? p1 : p2;
                while (pre.next != null) pre = pre.next;
            }
        }
        return dummy.next;
    }

    private ListNode cut(ListNode head, int len) {
        ListNode p = head;
        int count = 1;
        while (p != null && count < len) {
            p = p.next;
            count++;
        }

        ListNode q = null;
        if (p != null) {
            q = p.next;
            p.next = null;
        }
        return q;
    }

    private ListNode merge(ListNode p1, ListNode p2) {
        if (p1 == null) return p2;
        if (p2 == null) return p1;

        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                p.next = p1;
                p = p.next;
                p1 = p1.next;
            } else {
                p.next = p2;
                p = p.next;
                p2 = p2.next;
            }
        }

        p.next = (p1 != null) ? p1 : p2;
        return dummy.next;
    }

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode p10 = new ListNode(10);
        ListNode p1 = new ListNode(1);
        ListNode p3 = new ListNode(3);
        p10.next = p1;
        p1.next = p3;

        SortList148 sortList148 = new SortList148();
        ListNode r = sortList148.sortList(p10);

        System.out.println(r.val);
    }
}



