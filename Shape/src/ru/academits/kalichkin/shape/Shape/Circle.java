package ru.academits.kalichkin.shape.Shape;

public class Circle implements Shape {
    private String name = "Circle";
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public String toString () {
        return name;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getHeight() {
        return radius + radius;
    }

    @Override
    public double getWidth() {
        return radius + radius;
    }


}

