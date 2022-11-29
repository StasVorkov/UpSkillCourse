package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private Point centerOfCircle;
    private double radiusOfCircle;

    public Circle(Point center, double radius) {
        if (center == null) {
            try {
                throw new WrongPointException();
            } catch (WrongPointException e) {
                throw new RuntimeException("Invalid point", e);
            }
        }
        if (radius < 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }

        this.centerOfCircle = center;
        this.radiusOfCircle = radius;
    }

    @Override
    public double area() {
        return radiusOfCircle * radiusOfCircle * Math.PI;
    }

    @Override
    public String pointsToString() {
        String s = "(" + centerOfCircle.getX() + "," + centerOfCircle.getY() + ")";
        return s;
    }

    @Override
    public Point leftmostPoint() {
        return new Point(centerOfCircle.getX() - radiusOfCircle, centerOfCircle.getY());
    }

    @Override
    public String toString() {
        return "Circle[" + pointsToString() + radiusOfCircle + "]";
    }
}
