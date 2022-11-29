package com.epam.rd.autocode.bstprettyprint;

public class PrintNode
{
    private static final String RIGHT_UP_DOWN = "┤";
    private static final String RIGHT_UP = "┘";
    private static final String RIGHT_DOWN = "┐";
    private static final String LEFT_DOWN = "┌";
    private static final String LEFT_UP = "└";
    private static final String EMPTY_STRING = "";
    private static final char LINE_FEED = '\n';
    private static final String WHITESPACE = " ";
    private final int data;
    private final int numberOfWhitespaces;
    private ParentRelations parentRelations;
    private ChildrenRelations childrenRelations;

    public PrintNode(final TreeNode node) {
        data = node.getData();
        determineAncestorRelations(node);
        determineDescendantRelations(node);
        numberOfWhitespaces = determineNumberOfWhitespaces(node) - 1;
    }

    private void determineAncestorRelations(final TreeNode node)
    {
        if (node.parent() == null) {
            parentRelations = ParentRelations.ROOT;
        } else if (node.parent().getData() > node.getData()) {
            parentRelations = ParentRelations.LEFT;
        } else if (node.parent().getData() < node.getData()) {
            parentRelations = ParentRelations.RIGHT;
        }
    }

    private void determineDescendantRelations(final TreeNode node)
    {
        if (node.leftChild() == null && node.rightChild() == null) {
            childrenRelations = ChildrenRelations.NONE;
        } else if (node.leftChild() == null) {
            childrenRelations = ChildrenRelations.RIGHT;
        } else if (node.rightChild() == null) {
            childrenRelations = ChildrenRelations.LEFT;
        } else {
            childrenRelations = ChildrenRelations.BOTH;
        }
    }

    private int determineNumberOfWhitespaces(final TreeNode node)
    {
        if (node.parent() == null) {
            return 0;
        }
        int number = getDigitsNumber(node.parent().getData()) + 1;
        return number + determineNumberOfWhitespaces(node.parent());
    }

    private int getDigitsNumber(final int number) {
        return String.valueOf(number).length();
    }

    @Override
    public String toString() {
        return WHITESPACE.repeat(Math.max(0, numberOfWhitespaces)) +
                getParentCharacter() +
                data +
                getChildCharacter() +
                LINE_FEED;
    }

    private String getParentCharacter() {
        if (parentRelations == ParentRelations.LEFT) {
            return LEFT_DOWN;
        } else if (parentRelations == ParentRelations.RIGHT) {
            return LEFT_UP;
        } else {
            return EMPTY_STRING;
        }
    }

    private String getChildCharacter() {
        if (childrenRelations == ChildrenRelations.BOTH) {
            return RIGHT_UP_DOWN;
        } else if (childrenRelations == ChildrenRelations.LEFT) {
            return RIGHT_UP;
        } else if (childrenRelations == ChildrenRelations.RIGHT) {
            return RIGHT_DOWN;
        }else {
            return EMPTY_STRING;
        }
    }
    enum ParentRelations {
        LEFT, RIGHT, ROOT
    }

    enum ChildrenRelations {
        LEFT, RIGHT, BOTH, NONE
    }
}
