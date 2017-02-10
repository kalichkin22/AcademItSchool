package ru.academits.kalichkin.shape.Main;
import ru.academits.kalichkin.shape.Shape.*;


public class Main {
    public static void main(String[] args) {
        Shape s1 = new Rectangle(10, 2);
        Shape s2 = new Triangle(-1, 2, 3, 1, 5, 4);
        Shape s3 = new Square(5);
        Shape s4 = new Circle(3);
        Shape s5 = new Rectangle(5, 3);
        Shape s6 = new Triangle(0, 1, 2, 3, 2, 3);
        Shape s7 = new Square(1);
        Shape s8 = new Circle(2);
        Shape s9 = new Rectangle(6, 7);
        Shape s10 = new Circle(1.5);

        System.out.printf("%.2f, %.2f",s9.getArea(),s10.getArea());

    }



}

