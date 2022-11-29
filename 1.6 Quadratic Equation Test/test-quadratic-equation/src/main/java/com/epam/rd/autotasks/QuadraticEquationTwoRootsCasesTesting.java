package com.epam.rd.autotasks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    private double a;
    private double b;
    private double c;
    private String expected;
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2.0, 3.0, 1.0, "-1.0 -0.5"},
                {5.0, 25.0, 8.0, "-4.656385865284783 -0.3436141347152176"},
                {6.0, 10.0, 4.0, "-1.0 -0.6666666666666666"},
                {1.0, 4.0, 3.0, "-3.0 -1.0"},
        });
    }

    @Test
    public void testQuadraticEquationSingleRootCasesTesting() {
        String[] actualResult = quadraticEquation.solve(a, b, c).split(" ");
        String[] expectedResult = expected.split(" ");
        Arrays.sort(actualResult);
        Arrays.sort(expectedResult);
        Assert.assertArrayEquals(actualResult, expectedResult);
    }

}
