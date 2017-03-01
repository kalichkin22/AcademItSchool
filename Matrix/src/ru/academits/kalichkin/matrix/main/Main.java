package ru.academits.kalichkin.matrix.main;

import ru.academits.kalichkin.matrix.matrix.Matrix;
import ru.academits.kalichkin.vector.vector.Vector;


public class Main {
    public static void main(String[] args) {

        double[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        /*
        Vector[] vectors = {new Vector(7, 8, 9), new Vector(4, 5, 6)};

        Matrix a = new Matrix(2, 3);
        System.out.println(a);

        Matrix b = new Matrix(a);
        System.out.println(b);

        Matrix c = new Matrix(vectors);
        System.out.println(c);

        Matrix d = new Matrix(array);
        System.out.println(d);

*/
        Matrix d = new Matrix(array);
        System.out.println(d);
        Vector vector = new Vector(1, 2, 3);
        //d.setRow(1, vector);
        //System.out.println(d);
        //System.out.println(d.getColumn(1));
        //System.out.println(d.getRow(0));
        //System.out.println(d.transposition());
        //System.out.println(d.multiplicationByScalar(2));
        System.out.println(d.multiplyVector(vector));


    }
}