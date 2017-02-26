package ru.academits.kalichkin.shape.Shape;


public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public String toString () {
        return "Rectangle" + "(" + width + ")";
    }


    public double getArea() {
        return height * width;
    }


    public double getPerimeter() {
        return (width + height) * 2;
    }


    public double getWidth() {
        return width;
    }


    public double getHeight() {
        return height;
    }

    public boolean equals (Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && height == rectangle.height;
    }

    public int hashCode() {
        final int prime = 3;
        int hash = 1;
        hash = prime * hash + (int)width;
        hash = prime * hash + (int) height;
        return hash;
    }
}
