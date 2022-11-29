package com.epam.rd.autotasks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    private double a;
    private double b;
    private double c;
    private double expected;

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1.0, -2.0, 1.0, 1.0},
                {2.0, 4.0, 2.0, -1.0},
                {3.0, 6.0, 3.0, -1.0},
                {2.0, 1.0, 0.125, -0.25},
        });
    }

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Test
    public void testQuadraticEquationSingleRootCasesTesting() {
        Assert.assertEquals(Double.toString(expected), quadraticEquation.solve(a, b, c));
    }
}