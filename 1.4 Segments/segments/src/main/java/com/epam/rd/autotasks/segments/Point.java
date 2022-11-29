package com.epam.rd.autotasks.segments;

class Point implements Comparable<Point> {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(Point o) {
        if (this.getX() == o.getX() && this.getY()==o.getY()) {
            return 0;
        } else if (this.getX() > o.getX()) {
            return 1;
        } else return -1;
    }
}
