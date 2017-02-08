package ru.academits.kalichkin.shapes;


public abstract class Square implements Shapes {
    private double length;

    public Square (double length) {
        this.length = length;
    }

    public double getLength() {
        return this.length;
    }

    @Override
    public double getArea() {
        return this.length * this.length;
    }

    @Override
    public double getPerimeter() {
        return this.length * 4;
    }
}
