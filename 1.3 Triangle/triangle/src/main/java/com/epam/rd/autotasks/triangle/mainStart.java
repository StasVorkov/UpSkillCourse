package com.epam.rd.autotasks.triangle;

public class mainStart {
    public static void main(String[] args) {
        {
            double area = new Triangle(new Point(1, 3), new Point(3, 9), new Point(2, 6)).area();
            System.out.println(area);
        }
        {
            Point centroid = new Triangle(new Point(2, 5), new Point(-5, 4), new Point(3, 0)).centroid();

            System.out.println(centroid.getX());
            System.out.println(centroid.getY());
        }
    }
}
