package com.epam.rd.autocode.bstprettyprint;

import java.util.ArrayList;
import java.util.List;

public class PrintableTreeImplement implements PrintableTree {

    private static final char RIGHT_UP_DOWN = '┤';
    private static final char RIGHT_DOWN = '┐';
    private static final char UP_RIGHT = '┌';
    private static final char LINE = '│';
    private TreeNode root;

    @Override
    public void add(final int data) {
        if (root == null) {
            root = new TreeNode(data, null);
        } else {
            root.add(data);
        }
    }

    @Override
    public String prettyPrint() {

        StringBuilder builder = new StringBuilder();
        List<Integer> branchesPositions = new ArrayList<>();

        for (PrintNode printNode : getValues(root)) {
            char[] chars = printNode.toString().toCharArray();
            addBranches(chars, branchesPositions);
            refreshBranchesList(chars, branchesPositions);
            builder.append(String.valueOf(chars));
        }
        return builder.toString();
    }

    private List<PrintNode> getValues(final TreeNode node) {

        if (node == null) {
            return new ArrayList<>();
        }
        List<PrintNode> values = new ArrayList<>(getValues(node.leftChild()));
        values.add(new PrintNode(node));
        values.addAll(getValues(node.rightChild()));

        return values;
    }

    private void addBranches(final char[] chars, final List<Integer> branchesPositions) {

        for (int i = 0; i < chars.length; i++) {
            if (branchesPositions.contains(i) && chars[i] == ' ') {
                chars[i] = '│';
            }
        }
    }

    private void refreshBranchesList(final char[] chars, final List<Integer> branchesPositions) {

        for (int i = 0; i < chars.length; i++) {
            if (branchesPositions.contains(i) && chars[i] != LINE) {
                branchesPositions.remove(Integer.valueOf(i));
            }
            if (chars[i] == RIGHT_DOWN || chars[i] == UP_RIGHT
                    || chars[i] == RIGHT_UP_DOWN) {
                branchesPositions.add(i);
            }
        }
    }
}
