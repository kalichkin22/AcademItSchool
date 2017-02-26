package ru.academits.kalichkin.shape.Shape;


public class Square implements Shape {
    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public String toString() {
        return "Square" + "(" + length + ")";
    }

    public double getArea() {
        return length * length;
    }

    public double getPerimeter() {
        return length * 4;
    }

    public double getHeight() {
        return length;
    }

    public double getWidth() {
        return length;
    }


    public boolean equals (Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Square square = (Square) o;
        return length == square.length;
    }

    public int hashCode() {
        final int prime = 5;
        int hash = 1;
        hash = prime * hash + (int)length;
        return hash;
    }
}
