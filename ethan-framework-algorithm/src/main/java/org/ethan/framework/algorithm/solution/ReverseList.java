package org.ethan.framework.algorithm.solution;

/**
 * 链表翻转
 * @author Ethan Zhang
 */
public class ReverseList {

    public Node invoke(Node l) {
        Node next = null;
        Node pre = null;
        Node cur = l;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ReverseList solution = new ReverseList();
        System.out.println(solution.invoke(Node.of(1, 2, 3)));
    }

}
