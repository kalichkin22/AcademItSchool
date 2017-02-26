package ru.academits.kalichkin.matrix.main;


import ru.academits.kalichkin.matrix.matrix.Matrix;
import ru.academits.kalichkin.vector.Vector;

public class Main {
    public static void main(String[] args) {
/*
        double[][] array = {{1,3,3}, {4,5,6}};
        Vector vector = new Vector(3,5,6,7,8);
        Vector[] vectors = {new Vector(vector)};
*/
        Matrix a = new Matrix(3,4);
        System.out.println(a);

        Matrix b = new Matrix(a);
        System.out.println(b);

    }



}
