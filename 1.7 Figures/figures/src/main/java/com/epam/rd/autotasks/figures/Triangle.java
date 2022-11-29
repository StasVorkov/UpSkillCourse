package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private Point aVertice, bVertice, cVertice;    //   must be final?

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null || a.compareTo(b) == 0 || b.compareTo(c) == 0 || a.compareTo(c) == 0) {
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

    @Override
    public double area() {
        double abLength = getLength(aVertice, bVertice);
        double bcLength = getLength(bVertice, cVertice);
        double acLength = getLength(aVertice, cVertice);
        double halfPerimeter = (abLength + bcLength + acLength) / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - abLength) * (halfPerimeter - bcLength) * (halfPerimeter - acLength));
    }

    @Override
    public String pointsToString() {
        String s = "(" + aVertice.getX() + "," + aVertice.getY() + ")" + "(" + bVertice.getX() + "," + bVertice.getY() + ")" +
                "(" + cVertice.getX() + "," + cVertice.getY() + ")";
        return s;
    }

    @Override
    public Point leftmostPoint() {
        Point[] points = new Point[]{aVertice, bVertice, cVertice};
        Point min = points[0];
        for (Point point : points) {
            if (point.compareTo(min) < 0) {
                min = point;
            }
        }
        return min;
    }

    private double getLength(Point a, Point b) {
        return Math.hypot((a.getX() - b.getX()), (a.getY() - b.getY()));
    }

    @Override
    public String toString() {
        return "Triangle[" + pointsToString() + "]";
    }
}
