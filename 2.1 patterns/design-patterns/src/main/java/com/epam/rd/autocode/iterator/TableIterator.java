package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableIterator implements Iterable<String> {

    private final int[] rows;
    private final String[] columns;
    private int index = 0;
    private String[] transformedArray;

    public TableIterator(String[] columns, int[] rows) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            @Override
            public boolean hasNext() {
                return index < rows.length * columns.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                transformedArray = transformArrays();
                return transformedArray[index++];
            }
        };
    }

    private String[] transformArrays() {
        int indexTransform = 0;
        String[] transformedArray = new String[rows.length * columns.length];
        for (String column : columns) {
            for (int row : rows) {
                transformedArray[indexTransform++] = column + row;
            }
        }
        return transformedArray;
    }
}
