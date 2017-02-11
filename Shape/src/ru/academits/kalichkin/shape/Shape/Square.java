package ru.academits.kalichkin.shape.Shape;


public class Square implements Shape {
    private String name = "Square";
    private double length;

    public Square (double length) {
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public String toString () {
        return name;
    }

    @Override
    public double getArea() {
        return length * length;
    }

    @Override
    public double getPerimeter() {
        return length * 4;
    }

    @Override
    public double getHeight() {
        return length;
    }

    @Override
    public double getWidth() {
        return length;
    }
}
