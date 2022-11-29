package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {
    private Point aVertice, bVertice, cVertice, dVertice;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null ||
                a.compareTo(b) == 0 || b.compareTo(c) == 0 || c.compareTo(d) == 0 || a.compareTo(d) == 0) {
            try {
                throw new WrongPointException();
            } catch (WrongPointException e) {
                throw new RuntimeException("Invalid points", e);
            }
        }
        this.aVertice = a;
        this.bVertice = b;
        this.cVertice = c;
        this.dVertice = d;
    }

    @Override
    public double area() {
        double abLength = getLength(aVertice, bVertice);
        double bcLength = getLength(bVertice, cVertice);
        double cdLength = getLength(cVertice, dVertice);
        double daLength = getLength(dVertice, aVertice);
        double bdLengthDiagon = getLength(bVertice, dVertice);
        double areaOfTriangle1 = areaOfTriangle(abLength, daLength, bdLengthDiagon);
        double areaOfTriangle2 = areaOfTriangle(bcLength, cdLength, bdLengthDiagon);
        return areaOfTriangle1 + areaOfTriangle2;
    }

    @Override
    public String pointsToString() {
        String s = "(" + aVertice.getX() + "," + aVertice.getY() + ")" + "(" + bVertice.getX() + "," + bVertice.getY() + ")" +
                "(" + cVertice.getX() + "," + cVertice.getY() + ")" + "(" + dVertice.getX() + "," + dVertice.getY() + ")";
        return s;
    }

    @Override
    public Point leftmostPoint() {
        Point[] points = new Point[]{aVertice, bVertice, cVertice, dVertice};
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

    public double areaOfTriangle(double side1, double side2, double side3) {
        double halfPerimeter = (side1 + side2 + side3) / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));
    }

    @Override
    public String toString() {
        return "Quadrilateral[" + pointsToString() + "]";
    }
}
