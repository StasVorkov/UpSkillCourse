package com.ws.sort;

import com.ws.sort.sorting.Sorting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class ParametrizedRegularValuesTest {
    int[] input;
    int[] expected;

    Sorting sorting = new Sorting();

    public ParametrizedRegularValuesTest(int[] input, int[] expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new int[][][]{
                {{-20, 3, 7, 5, 89}, {-20, 3, 5, 7, 89}},
                {{0, 23, -5, Integer.MAX_VALUE}, {-5, 0, 23, Integer.MAX_VALUE}},
                {{Integer.MAX_VALUE, 5, -100, Integer.MIN_VALUE}, {Integer.MIN_VALUE, -100, 5, Integer.MAX_VALUE}},
                {{100, 0, -9}, {-9, 0, 100}},
                {{100, 0, -9, 1, 2, 3, 4, 5, 6, 7}, {-9, 0, 1, 2, 3, 4, 5, 6, 7, 100}},
        });
    }

    @Test
    public void parametrizedRegularValuesTest() {
        sorting.sort(input);
        Assert.assertArrayEquals(input, expected);
    }
}
