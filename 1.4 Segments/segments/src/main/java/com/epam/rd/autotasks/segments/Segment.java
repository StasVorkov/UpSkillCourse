package com.epam.rd.autotasks.segments;

class Segment {
    private final Point start;
    private final Point end;
    private double p1X, p1Y, p2X, p2Y;

    public Segment(Point start, Point end) {
        if (start == null || end == null || end.compareTo(start)==0 || end.equals(start)) {
            throw new IllegalArgumentException();
        } else {
            this.start = start;
            this.end = end;
            p1X = this.start.getX();
            p1Y = this.start.getY();
            p2X = this.end.getX();
            p2Y = this.end.getY();
        }
    }

    public double length() {
        return Math.hypot((p2X - p1X), (p2Y - p1Y));
    }

    Point middle() {
        double xMiddle;
        double yMiddle;

        //1 calculate xMiddle
        if (p1X == p2X) {
            xMiddle = p1X;
        } else xMiddle = (p1X + p2X) / 2;

        //2 calculate yMiddle
        if (p1Y == p2Y) {
            yMiddle = p1Y;
        } else yMiddle = (p1Y + p2Y) / 2;

        return new Point(xMiddle, yMiddle);
    }

    Point intersection(Segment another) {

        double p3X = another.start.getX();
        double p3Y = another.start.getY();
        double p4X = another.end.getX();
        double p4Y = another.end.getY();

        //1 check order of points of 1st segment
        checkOrderOfPointsP1P2(p1X, p2X, p1Y, p2Y);

        //2 check order of points of 2 segment
        if (p4X < p3X) {
            double tmpX = p3X;
            p3X = p4X;
            p4X = tmpX;
            double tmpY = p3Y;
            p3Y = p4Y;
            p4Y = tmpY;
        }

        //3 Return null if there is no such point.
        if (p2X < p3X) {
            return null;
        }

        //4 solve the equation
        // f1(x) = A1*x + b1 = y
        // f2(x) = A2*x + b2 = y

        double A1 = (p1Y - p2Y) / (p1X - p2X);
        double A2 = (p3Y - p4Y) / (p3X - p4X);
        double b1 = p1Y - A1 * p1X;
        double b2 = p3Y - A2 * p3X;

        //5 Return null if segments are parallel
        if (A1 == A2) {
            return null;
        }

        //6 calculate point of intersection Xa
        double Xa = (b2 - b1) / (A1 - A2);

        //7 check: intersection point must be on both segments.
        if ((Xa < Math.max(p1X, p3X)) || (Xa > Math.min(p2X, p4X))) {
            return null;
        }

        //8 calculate point of intersection Ya
        double Ya = A1 * (b2 - b1) / (A1 - A2) + b1;
        return new Point(Xa, Ya);
    }

    private void checkOrderOfPointsP1P2(double p1X, double p2X, double p1Y, double p2Y) {
        if ((p2X) < (p1X)) {
            this.p1X = p2X;
            this.p2X = p1X;
            this.p1Y = p2Y;
            this.p2Y = p1Y;
        }
    }
}

