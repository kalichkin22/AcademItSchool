package ru.academits.kalichkin.shape.Main;

import ru.academits.kalichkin.shape.Shape.*;


public class Main {
    private static Shape getMaxShape(Shape[] shapes) {
        Shape areaRecord = shapes[0];

        for (int i = 1; i < shapes.length; ++i) {
            if (shapes[i].getArea() > areaRecord.getArea()) {
                areaRecord = shapes[i];
            }
        }
        return areaRecord;
    }


    public static void main(String[] args) {

        Shape[] shapes = new Shape[8];

        shapes[0] = new Rectangle(10, 2);
        shapes[1] = new Triangle(-1, 2, 3, 1, 5, 4);
        shapes[2] = new Square(5);
        shapes[3] = new Circle(2);
        shapes[4] = new Rectangle(5, 3);
        shapes[5] = new Triangle(0, 1, 2, 3, 2, 3);
        shapes[6] = new Square(1);
        shapes[7] = new Circle(1);

        Shape a = getMaxShape(shapes);

        System.out.printf("Фигура с максимальной площадью: %s.%nПлощадь фигуры: %.2f", a.toString(), a.getArea());
    }
}




