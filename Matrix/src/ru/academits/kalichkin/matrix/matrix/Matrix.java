package ru.academits.kalichkin.matrix.matrix;

import ru.academits.kalichkin.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int n, int m) {
        rows = new Vector[m];
        for (int i = 0; i < rows.length; ++i) {
            rows[i] = new Vector(n);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = matrix.rows;
    }

    public Matrix(double[][] array) {

    }

    public Matrix(Vector[] vectors) {
        this.rows = vectors;

    }

    public String toString() {
        return Arrays.deepToString(rows).replace("[", "{").replace("]", "}");
    }

}
