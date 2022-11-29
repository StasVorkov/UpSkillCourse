package com.epam.rd.autotasks;

public class QuadraticEquation {

    public String solve(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException();
        }
        double root1;
        double root2;
        String result;
        double discriminant = b * b - 4 * a * c;
        if (discriminant > 0) {
            root1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            root2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            result = ((root1) + " " + (root2));
        } else if (discriminant == 0) {
            root1 = -b / (2 * a);
            result = Double.toString(root1);
        } else result = ("no roots");
        return result;
    }
}
