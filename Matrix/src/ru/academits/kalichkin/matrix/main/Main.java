package ru.academits.kalichkin.matrix.main;


import ru.academits.kalichkin.matrix.matrix.Matrix;
import ru.academits.kalichkin.vector.Vector;

public class Main {
    public static void main(String[] args) {

        double[][] array = {{1,3,3}, {4,5,6}};
        Vector[] vectors = {new Vector(4,6,8), new Vector(3,6,6)};

        Matrix a = new Matrix(3,4);
        System.out.println(a);

        Matrix b = new Matrix(a);
        System.out.println(b);

        Matrix c = new Matrix(vectors);
        System.out.println(c);

    }



}
