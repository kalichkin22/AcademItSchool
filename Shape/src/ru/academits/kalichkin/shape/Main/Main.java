package ru.academits.kalichkin.shape.Main;
import ru.academits.kalichkin.shape.Shape.*;


public class Main {
    private static double getMaxArea(double[] a) {
        double areaRecord = a[0];

        for (int i = 1; i < a.length; ++i) {
            if (a[i] > areaRecord) {
                areaRecord = a[i];
            }
        }
        return areaRecord;
    }

    public static void main(String[] args) {
        Shape s1 = new Rectangle(10, 2);
        Shape s2 = new Triangle(-1, 2, 3, 1, 5, 4);
        Shape s3 = new Square(5);
        Shape s4 = new Circle(3);
        Shape s5 = new Rectangle(5, 3);
        Shape s6 = new Triangle(0, 1, 2, 3, 2, 3);
        Shape s7 = new Square(1);
        Shape s8 = new Circle(2);

        double [] a = {s1.getArea(), s2.getArea(), s3.getArea(), s4.getArea(),
                s5.getArea(), s6.getArea(), s7.getArea(), s8.getArea()};

        System.out.println(getMaxArea(a));
    }
}




