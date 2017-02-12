package ru.academits.kalichkin.shape.Shape;

public class Triangle implements Shape {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;


    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public String toString() {
        return "Triangle" + "(" + x1 + ")";
    }

    private static double getLength(double x1, double x2, double y1, double y2) {
        return (Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));

    }


    public double getWidth() {
        return Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3));
    }


    public double getHeight() {
        return Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3));
    }


    public double getArea() {
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p - getLength(x1, x2, y1, y2)) + (p - getLength(x2, x3, y2, y3)) + (p - getLength(x1, x3, y1, y3)));
    }


    public double getPerimeter() {
        return getLength(x1, x2, y1, y2) + getLength(x2, x3, y2, y3) + getLength(x1, x3, y1, y3);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Triangle triangle = (Triangle) o;
        return x1 == triangle.x1 && y1 == triangle.y1 && x2 == triangle.x2
                && y2 == triangle.y2 && x3 == triangle.x3 && y3 == triangle.y3;
    }

    public int hashCode() {
        final int prime = 11;
        int hash = 1;
        hash = prime * hash + (int)x1;
        hash = prime * hash + (int)x2;
        hash = prime * hash + (int)x3;
        hash = prime * hash + (int)y1;
        hash = prime * hash + (int)y2;
        hash = prime * hash + (int)y3;
        return hash;
    }

}
