package com.epam.rd.autocode.bstprettyprint;

public class TreeNode {

    private final int data;
    private final TreeNode parent;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(final int data, final TreeNode parent) {
        this.data = data;
        this.parent = parent;
    }

    public int getData() {
        return data;
    }

    public TreeNode leftChild() {
        return leftChild;
    }

    public TreeNode rightChild() {
        return rightChild;
    }

    public TreeNode parent() {
        return parent;
    }

    void add(final int data) {
        if (data < this.data) {
            if (leftChild == null) {
                leftChild = new TreeNode(data, this);
            } else {
                leftChild.add(data);
            }
        } else if (data > this.data) {
            if (rightChild == null) {
                rightChild = new TreeNode(data, this);
            } else {
                rightChild.add(data);
            }
        }
    }
}
