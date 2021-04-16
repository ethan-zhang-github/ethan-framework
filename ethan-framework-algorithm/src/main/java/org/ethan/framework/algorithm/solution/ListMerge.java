package org.ethan.framework.algorithm.solution;

/**
 * 有序链表合并
 * @author Ethan Zhang
 */
public class ListMerge {

    public Node invoke(Node l1, Node l2) {
        Node dummyHead = new Node(0);
        Node res = dummyHead;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                dummyHead.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                dummyHead.next = l1;
                l1 = l1.next;
            } else if (l1.val <= l2.val) {
                dummyHead.next = l1;
                l1 = l1.next;
            } else {
                dummyHead.next = l2;
                l2 = l2.next;
            }
            dummyHead = dummyHead.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListMerge solution = new ListMerge();
        System.out.println(solution.invoke(Node.of(1, 3, 5, 7, 9, 14, 15), Node.of(2, 4, 6, 8, 10, 11, 19)));
    }

}
