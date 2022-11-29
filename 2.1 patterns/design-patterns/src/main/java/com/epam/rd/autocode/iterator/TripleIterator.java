package com.epam.rd.autocode.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class TripleIterator implements Iterator<Integer> {

    private static final int AMOUNT_OF_ITERATIONS = 3;

    private final int[] array;
    private int index = 0;

    public TripleIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length * AMOUNT_OF_ITERATIONS;
    }

    @Override
    public Integer next() {

        int[] duplicated;

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        duplicated = Arrays.stream(array)
                .flatMap(i -> IntStream.of(i, i, i))
                .toArray();

        return duplicated[index++];
    }
}
