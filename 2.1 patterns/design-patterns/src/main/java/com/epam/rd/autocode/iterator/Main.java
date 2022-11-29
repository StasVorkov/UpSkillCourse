package com.epam.rd.autocode.iterator;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] sArray = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"};

        TableIterator tableIterator = new TableIterator(sArray, array);
        while (tableIterator.iterator().hasNext()) {
            System.out.println(tableIterator.iterator().next());
        }
    }
}
