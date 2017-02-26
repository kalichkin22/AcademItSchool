package ru.academits.kalichkin.matrix.matrix;

import ru.academits.kalichkin.matrix.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] matrix;

    public Matrix(int n, int m) {

    }

    public Matrix(Matrix matrix) {
        this.matrix = matrix.matrix;
    }

    public Matrix(double[][] array) {

    }

    public Matrix(Vector[] vectors) {

    }

    public String toString() {
        return Arrays.deepToString(matrix).replace("[", "{").replace("]", "}");
    }

}
