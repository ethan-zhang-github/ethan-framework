package org.ethan.framework.algorithm.solution;

/**
 * 判断链表是否有环
 * @author Ethan Zhang
 */
public class IsListHasCycle {

    public boolean invoke(Node head) {
        Node p = head;
        Node q = head;
        while (p != null && p.next != null) {
            p = p.next.next;
            q = q.next;
            if (p == q) {
                return true;
            }
        }
        return false;
    }

}
