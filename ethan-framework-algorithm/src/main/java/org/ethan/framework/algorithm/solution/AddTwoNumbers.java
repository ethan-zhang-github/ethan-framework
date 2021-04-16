package org.ethan.framework.algorithm.solution;

/**
 * 链表实现两数相加
 * @author Ethan Zhang
 */
public class AddTwoNumbers {

    public Node invoke(Node l1, Node l2) {
        Node p = l1, q = l2, dummyHead = new Node(0), curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = carry + x + y;
            curr.next = new Node(sum % 10);
            curr = curr.next;
            carry = sum/10;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new Node(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers solution = new AddTwoNumbers();
        System.out.println(solution.invoke(Node.of(2, 4, 3), Node.of(5, 6, 4)));
    }

}
