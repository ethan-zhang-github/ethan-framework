package org.ethan.framework.algorithm.solution;

/**
 * 删除链表倒数第K个节点
 * @author Ethan Zhang
 */
public class RemoveNthFromEnd {

    public Node invoke(Node l, int k) {
        Node p = l;
        Node q = l;
        int pSteps = 0;
        while (p != null) {
            if (pSteps >= k + 1) {
                q = q.next;
            }
            pSteps++;
            p = p.next;
        }
        if (q != null) {
            q.next = q.next.next;
        }
        return l;
    }

    public static void main(String[] args) {
        RemoveNthFromEnd solution = new RemoveNthFromEnd();
        System.out.println(solution.invoke(Node.of(1, 2, 3, 4, 5, 6, 7), 3));
    }

}
