package com.epam.rd.autotasks.triangle;

class Triangle {

    private final Point aVertice;
    private final Point bVertice;
    private final Point cVertice;

    public Triangle(Point a, Point b, Point c) {
        if (    isPointsIsNull(a, b, c) ||
                isTriangleDegenerative(a, b, c) ||
                arePointsBelongToAxisXY(a, b, c) ||
                arePointsBelongToOneLine(a, b, c)) {
            try {
                throw new WrongPointException();
            } catch (WrongPointException e) {
                throw new RuntimeException("Invalid points", e);
            }
        }
        this.aVertice = a;
        this.bVertice = b;
        this.cVertice = c;
    }

    public double area() {
        double abLength = getLength(aVertice, bVertice);
        double bcLength = getLength(bVertice, cVertice);
        double acLength = getLength(aVertice, cVertice);
        double halfPerimeter = (abLength + bcLength + acLength) / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - abLength) * (halfPerimeter - bcLength) * (halfPerimeter - acLength));
    }

    public Point centroid() {

        double xCentroid = (aVertice.getX() + bVertice.getX() + cVertice.getX()) / 3;
        double yCentroid = (aVertice.getY() + bVertice.getY() + cVertice.getY()) / 3;
        return new Point(xCentroid, yCentroid);
    }

    private double getLength(Point a, Point b) {
        return Math.hypot((a.getX() - b.getX()), (a.getY() - b.getY()));
    }

    private boolean isPointsIsNull(Point a, Point b, Point c) {
        return a == null || b == null || c == null;
    }

    private boolean isTriangleDegenerative(Point a, Point b, Point c) {
        return a.compareTo(b) == 0 || b.compareTo(c) == 0 || a.compareTo(c) == 0;
    }

    private boolean arePointsBelongToAxisXY(Point a, Point b, Point c) {
        return a.getX() == b.getX() && b.getX() == c.getX() || a.getY() == b.getY() && b.getY() == c.getY();
    }

    private boolean arePointsBelongToOneLine(Point a, Point b, Point c) {
        return (c.getX() - a.getX()) / (b.getX() - a.getX()) == (c.getY() - a.getY()) / (b.getY() - a.getY());
    }
}
