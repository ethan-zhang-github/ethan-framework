package org.ethan.framework.algorithm.tree;

public class TreeNode<T> {

    public T data;

    public TreeNode<T> left;

    public TreeNode<T> right;

    public TreeNode() {}

    public TreeNode(T data) {
        this.data = data;
    }

}
