package ru.academits.kalichkin.shapes;


public abstract class Rectangle implements Shapes {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return this.height * this.width;
    }

    @Override
    public double getPerimeter() {
        return (this.width + this.height) * 2;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

}
