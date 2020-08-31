package randy.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode-cn.com/problems/merge-k-sorted-lists/submissions/
// 23
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        for (int i = 1; i < lists.length; i *= 2) {
            for (int j = 0; j < lists.length - i; j += 2 * i) {
                lists[j] = merge(lists[j], lists[j + i]);
            }
        }
        return lists[0];
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        return helper(lists, 0, lists.length - 1);
    }

    private ListNode helper(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        int m = (l + r) >> 1;
        return merge(helper(lists, l, m), helper(lists, m + 1, r));
    }

    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparing(n -> n.val));
        for (ListNode n: lists) {
            if (n != null) {
                queue.add(n);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (!queue.isEmpty()) {
            pre.next = queue.poll();
            pre = pre.next;
            if (pre.next != null) {
                queue.add(pre.next);
            }
        }

        return dummy.next;
    }
}
