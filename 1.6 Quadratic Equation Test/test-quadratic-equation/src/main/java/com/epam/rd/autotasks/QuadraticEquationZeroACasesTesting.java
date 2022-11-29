package com.epam.rd.autotasks;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class QuadraticEquationZeroACasesTesting {
    private double a;
    private double b;
    private double c;
    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    public QuadraticEquationZeroACasesTesting(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 2.0, 2.0},
                {0, 3.0, 3.0},
                {0, -5.0, -5.0},
                {0, -6.0, -6.0},
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuadraticEquationSingleRootCasesTesting() {
        quadraticEquation.solve(a, b, c);
    }
}
