package org.ethan.framework.algorithm.solution;

/**
 * 找到链表中倒数第K个节点
 * @author Ethan Zhang
 */
public class FindKthToTail {

    public Node invoke(Node l, int k) {
        Node p = l;
        Node q = l;
        int pSteps = 0;
        while (p != null) {
            if (pSteps >= k) {
                q = q.next;
            }
            pSteps ++;
            p = p.next;
        }
        if (pSteps < k) {
            return null;
        }
        return q;
    }

    public static void main(String[] args) {
        FindKthToTail solution = new FindKthToTail();
        System.out.println(solution.invoke(Node.of(1, 2, 3, 4, 5, 6, 7), 6));
    }

}
