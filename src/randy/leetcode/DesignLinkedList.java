package randy.leetcode;

// https://leetcode-cn.com/problems/design-linked-list/
// 707. 设计链表
public class DesignLinkedList {
    class Node {
        int val;
        Node prev;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    /** Initialize your data structure here. */
    public DesignLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        Node p = getNode(index);
        return p != null ? p.val : -1;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node p = new Node(val);
        p.next = head;
        if (head != null) {
            head.prev = p;
        } else {
            tail = p;
        }
        head = p;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node p = new Node(val);
        p.prev = tail;
        if (tail != null) {
            tail.next = p;
        } else {
            head = p;
        }
        tail = p;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index <= 0)
            addAtHead(val);
        else if (index == size)
            addAtTail(val);
        else {
            Node p = getNode(index);
            Node q = new Node(val);
            q.prev = p.prev;
            p.prev.next = q;
            q.next = p;
            p.prev = q;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        Node p = getNode(index);
        if (p != null) {
            if (p.prev != null) {
                p.prev.next = p.next;
            } else {
                head = p.next;
            }
            if (p.next != null) {
                p.next.prev = p.prev;
            } else {
                tail = p.prev;
            }
            size--;
        }
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size)
            return null;
        Node p = head;
        int cnt = 0;
        while (cnt < index) {
            p = p.next;
            cnt++;
        }
        return p;
    }

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
    public static void main(String[] args) {
        DesignLinkedList list = new DesignLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 2);
        System.out.println(list.get(1));
        list.deleteAtIndex(1);
        System.out.println(list.get(1));
    }
}
