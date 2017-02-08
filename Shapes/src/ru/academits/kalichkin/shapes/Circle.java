package ru.academits.kalichkin.shapes;

public abstract class Circle implements Shapes {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public double getArea() {
        return Math.PI * this.radius * this.radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * this.radius;
    }

    @Override
    public double getHeight() {
        return this.radius + this.radius;
    }

    @Override
    public double getWidth() {
        return this.radius + this.radius;
    }


}

