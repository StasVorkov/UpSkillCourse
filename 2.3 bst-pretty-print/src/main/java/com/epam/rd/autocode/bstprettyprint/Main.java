package com.epam.rd.autocode.bstprettyprint;

public class Main {
    public static void main(String[] args) {
        PrintableTree printableTreeImplement = PrintableTree.getInstance();
        printableTreeImplement.add(53);
        printableTreeImplement.add(3);
        printableTreeImplement.add(122);
        printableTreeImplement.add(756);
        printableTreeImplement.add(94);
        printableTreeImplement.add(88);
        printableTreeImplement.add(566);
        System.out.println(printableTreeImplement.prettyPrint());
    }
}
