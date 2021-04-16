package org.ethan.framework.algorithm.solution;

public class Node {

    public int val;

    public Node next;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(val);
        Node cur = this;
        while (cur.next != null) {
            res.append("->").append(cur.next.val);
            cur = cur.next;
        }
        return res.toString();
    }

    public static Node of(int... arr) {
        if (arr.length == 0) {
            return null;
        }
        Node dummyHead = new Node(0);
        Node cur = dummyHead;
        for (int val : arr) {
            cur.next = new Node(val);
            cur = cur.next;
        }
        return dummyHead.next;
    }

}
