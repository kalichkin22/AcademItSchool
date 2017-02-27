package ru.academits.kalichkin.matrix.main;


import ru.academits.kalichkin.matrix.matrix.Matrix;
import ru.academits.kalichkin.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        double[][] array = {{1, 2, 3}, {4, 5, 6}};
        Vector[] vectors = {new Vector(7, 8, 9), new Vector(4, 5, 6)};
        Vector vector = new Vector(3, 8, 0);

        Matrix a = new Matrix(2, 3);
        System.out.println(a);

        Matrix b = new Matrix(a);
        System.out.println(b);

        Matrix c = new Matrix(vectors);
        System.out.println(c);

        Matrix d = new Matrix(array);
        System.out.println(d);

        d.setRow(1, new Vector(3, 6, 8));
        System.out.println(d);

        // System.out.println(d.getRow(1));

        System.out.println(d.transportMatrix());
    }
}
