package com.epam.rd.autotasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortingTest {

    Sorting sorting = new Sorting();

    @Test(expected = IllegalArgumentException.class)
    public void testNullCase() {
        sorting.sort(null);
    }

    @Test(expected = Test.None.class)
    public void testEmptyCase() {
        sorting.sort(new int[]{});
    }

    @Test
    public void testSingleElementArrayCase() {
        int[] array1 = new int[]{5};
        int[] array2 = Arrays.copyOf(array1, array1.length);
        sorting.sort(array1);
        Assert.assertArrayEquals(array2, array1);
    }

    @Test
    public void testSortedArraysCase() {

        int[] array1 = new int[]{1, 5, 8, 10};
        int[] array2 = new int[]{1, 5, 8, 10};
        sorting.sort(array1);
        Assert.assertArrayEquals(array2, array1);
    }

    @Test
    public void testOtherCases() {
        int[] array1 = new int[]{3, 2, 1};
        int[] array2 = new int[]{1, 2, 3};
        sorting.sort(array1);
        Assert.assertArrayEquals(array2, array1);
    }
}