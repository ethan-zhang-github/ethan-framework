package org.ethan.framework.algorithm.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public void add(List<T> list) {
        list.forEach(this::add);
    }

    public void add(T data) {
        TreeNode<T> node = new TreeNode<>(data);
        root = add(root, node);
    }

    public void levelOrder(Consumer<T> consumer) {
        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> remove = queue.remove();
            if (remove.left != null) {
                queue.add(remove.left);
            }
            if (remove.right != null) {
                queue.add(remove.right);
            }
            consumer.accept(remove.data);
        }
    }

    public void preOrder(Consumer<T> consumer) {
        preOrder(root, consumer);
    }

    private void preOrder(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) {
            return;
        }
        consumer.accept(root.data);
        preOrder(root.left, consumer);
        preOrder(root.right, consumer);
    }

    public void midOrder(Consumer<T> consumer) {
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> cur = root;
        stack.push(cur);
        while (!stack.isEmpty()) {
            while (cur.left != null) {
                stack.push(cur.left);
                cur = cur.left;
            }
            TreeNode<T> pop = stack.pop();
            consumer.accept(pop.data);
            if (pop.right != null) {
                stack.push(pop.right);
                cur = pop.right;
            }
        }
    }

    public void postOrder(Consumer<T> consumer) {
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> cur = root;
        stack.push(cur);
        TreeNode<T> pre = null;
        while (!stack.isEmpty()) {
            while (cur.left != null) {
                stack.push(cur.left);
                cur = cur.left;
            }
            TreeNode<T> peek = stack.peek();
            if (peek.right == null || peek.right == pre) {
                TreeNode<T> pop = stack.pop();
                consumer.accept(pop.data);
                pre = pop;
            } else {
                stack.push(peek.right);
                cur = peek.right;
            }
        }
    }

    private TreeNode<T> add(TreeNode<T> root, TreeNode<T> node) {
        if (root == null) {
            return node;
        }
        if (node.data.compareTo(root.data) <= 0) {
            root.left = add(root.left, node);
        } else {
            root.right = add(root.right, node);
        }
        return root;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(Arrays.asList(45, 47, 23, 84, 7, 16, 34, 19, 5, 43, 9));
        tree.preOrder(System.out::println);
    }

}
