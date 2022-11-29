package com.epam.rd.autotasks;

import java.text.DecimalFormat;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        calculateAndPrintRoots(a,b,c);
    }

    private static void calculateAndPrintRoots(double a, double b, double c) {
        double root1;
        double root2;
        double discriminant = b * b - 4 * a * c;
        DecimalFormat dF = new DecimalFormat("#.#######");
        if (discriminant > 0) {
            root1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            root2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            System.out.print(dF.format(root1) + " " + dF.format(root2));
        } else if (discriminant == 0) {
            root1 = -b / (2 * a);
            System.out.print(dF.format(root1));
        } else System.out.println("no roots");
    }
}