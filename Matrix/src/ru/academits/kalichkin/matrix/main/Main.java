package ru.academits.kalichkin.matrix.main;

import ru.academits.kalichkin.matrix.matrix.Matrix;
import ru.academits.kalichkin.vector.vector.Vector;


public class Main {
    public static void main(String[] args) {
        try {
            double[][] array = {{2, 1}, {-3, 0}};
            double[][] array2 = {{5, -1, 6}, {-3, 0, 7}};

            /*

        Matrix a = new Matrix(2, 3);
        System.out.println(a);
        Matrix b = new Matrix(a);
        System.out.println(b);
        Matrix c = new Matrix(vectors);
        System.out.println(c);
        Matrix d = new Matrix(array);
        System.out.println(d);
        Vector[] vectors = {new Vector(7, 8,9), new Vector(4, 5, 6)};
        Matrix c = new Matrix(vectors);
        System.out.println(c);
*/


            Matrix d = new Matrix(array);
            Matrix e = new Matrix(array2);
            System.out.println(d);
            //System.out.println(e);
            //Vector vector = new Vector(1, 2, 3);
            //d.setRow(1, vector);
            //System.out.println(d);
            //System.out.println(d.getColumn(2));
            //System.out.println(d.getRow(0));
            System.out.println(d.transposition());
            //System.out.println(d.multiplicationByScalar(2));
            //System.out.println(d.multiplyVector(vector));
            //System.out.println(d.addition(e));
            //System.out.println(Matrix.getAddition(d, e));
            //System.out.println(Matrix.getMultiplication(d, e));


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}