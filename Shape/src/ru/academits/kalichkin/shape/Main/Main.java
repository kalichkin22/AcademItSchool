package ru.academits.kalichkin.shape.Main;

import ru.academits.kalichkin.shape.Shape.*;

import java.util.Arrays;


public class Main {
    private static Shape getMaxShape(Shape[] shapes) {
        Shape record = shapes[0];

        for (int i = 1; i < shapes.length; ++i) {
            if (shapes[i].getArea() > record.getArea()) {
                record = shapes[i];
            }
        }
        return record;
    }

    private static Shape getSecondPerimeter(Shape[] shapes) {
        double[] array = new double[shapes.length];
        Shape p = shapes[0];
        int i = 0;

        for (Shape e : shapes) {
            array[i] = e.getPerimeter();
            ++i;
        }

        Arrays.sort(array);

        for (Shape e : shapes) {
            if (array[array.length - 2] == e.getPerimeter()) {
                p = e;
            }
        }
        return p;
    }

    public static void main(String[] args) {

        Shape[] shapes = {new Rectangle(10, 2), new Triangle(-1, 2, 3, 1, 5, 4), new Square(1),
                new Circle(2), new Rectangle(10, 2), new Triangle(0, 1, 2, 3, 2, 3), new Square(1), new Circle(1)};


        Shape s = getMaxShape(shapes);
        Shape p = getSecondPerimeter(shapes);
        System.out.printf("Фигура с максимальной площадью: %s.%nПлощадь фигуры: %.2f%n", s.toString(), s.getArea());
        System.out.printf("Фигура с вторым по величине периметром: %s.%nПериметр фигуры: %.2f%n", p.toString(), p.getPerimeter());

        System.out.println(shapes[0].equals(shapes[4]));

        System.out.println(shapes[5].hashCode());
        System.out.println(shapes[1].hashCode());

    }
}




