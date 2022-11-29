package com.ws.sort;

import com.ws.sort.sorting.Sorting;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortingTest {

    Sorting sorting = new Sorting();

    @Test(expected = IllegalArgumentException.class)
    public void testNullCase() {
        sorting.sort(null);
    }


    @Test (expected = IllegalArgumentException.class)
    public void testEmptyCase() {
        int[] array1 = new int[]{};
        sorting.sort(array1);
    }

    @Test
    public void testSingleElementArrayCase() {
        int[] array1 = new int[]{5};
        int[] array2 = Arrays.copyOf(array1, array1.length);
        sorting.sort(array1);
        Assert.assertArrayEquals(array1, array2);
    }

    @Test
    public void testSortedArraysCase() {
        int[] array1 = new int[]{1, 5, 8, 10};
        int[] array2 = new int[]{1, 5, 8, 10};
        sorting.sort(array1);
        Assert.assertArrayEquals(array2, array1);
    }
}