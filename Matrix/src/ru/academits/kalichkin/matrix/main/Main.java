package ru.academits.kalichkin.matrix.main;

import ru.academits.kalichkin.matrix.matrix.Matrix;
import ru.academits.kalichkin.vector.vector.Vector;


public class Main {
    public static void main(String[] args) {
        try {
            double[][] array = {{1, 2, 3}, {6, -5, 2}, {-4, 2, 0}};
            double[][] array2 = {{5, -1}, {-3, 0}};

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
            //System.out.println(d);
            //System.out.println(e);
            Vector vector = new Vector(1, 2);
            //System.out.println(d.getRowsCount());
            //System.out.println(d.getColumnsCount());
            //d.setRow(1, e.getColumn(0));
            //System.out.println(d);
            //System.out.println(d.getColumn(0));
            //System.out.println(d.getRow(0));
            //System.out.println(d.transposition());
            //System.out.println(d.multiplicationByScalar(2));
            //System.out.println(d.multiplyVector(vector));
            //System.out.println(d.addition(e));
            //System.out.println(d.subtraction(e));
            //System.out.println(Matrix.getAddition(d, e));
            //System.out.println(Matrix.getSubtraction(d, e));
            //System.out.println(Matrix.getMultiplication(d, e));

            System.out.println(d.determinant());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}