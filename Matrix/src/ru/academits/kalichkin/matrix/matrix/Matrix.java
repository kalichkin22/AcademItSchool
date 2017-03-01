package ru.academits.kalichkin.matrix.matrix;

import ru.academits.kalichkin.vector.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int columns, int rows) {
        this.rows = new Vector[columns];
        for (int i = 0; i < this.rows.length; ++i) {
            this.rows[i] = new Vector(rows);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(double[][] array) {
        this.rows = new Vector[array.length];
        for (int i = 0; i < this.rows.length; ++i) {
            this.rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        this.rows = vectors;

    }

    public int getSize() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index >= this.rows.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы массива");
        } else {
            return new Vector(rows[index]);
        }
    }

    public void setRow(int index, Vector vector) {
        if (index >= this.rows.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы массива");
        } else {
            this.rows[index] = new Vector(vector);
        }
    }

    public Vector getColumn(int index) {
        if (index > this.rows.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы массива");
        } else {
            Vector column = new Vector(this.rows.length);
            for (int i = 0; i < this.rows.length; ++i) {
                column.setElement(i, this.rows[i].getElement(index));
            }
            return column;
        }
    }

    public Matrix transposition() {
        Matrix transposition = new Matrix(this.rows[0].getSize(), this.rows.length);
        for (int i = 0; i < this.rows[0].getSize(); i++) {
            transposition.setRow(i, this.getColumn(i));
        }
        return transposition;
    }

    public Matrix multiplicationByScalar(double number) {
        for (int i = 0; i < this.rows.length; ++i) {
            this.rows[i] = this.rows[i].multiplicationByScalar(number);
        }
        return this;
    }


    public Vector multiplyVector(Vector vector) {
        if (vector.getSize() != this.rows[0].getSize()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        }
        Vector multiply = new Vector(this.rows.length);
        for (int i = 0; i < this.rows.length; i++) {
            double sum = 0;
            for (int j = 0; j < this.rows[0].getSize(); j++) {
                sum += this.getRow(i).getElement(j) * vector.getElement(j);
            }
            multiply.setElement(i, sum);
        }
        return multiply;
    }


    public Matrix addition(Matrix matrix) {
        if (this.rows.length != matrix.rows.length
                || this.rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        }
        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = this.rows[i].addition(matrix.rows[i]);
            setRow(i, this.rows[i]);
        }
        return this;
    }

    public Matrix subtraction(Matrix matrix) {
        if (this.rows.length != matrix.rows.length
                || this.rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        }
        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = this.rows[i].subtraction(matrix.rows[i]);
            setRow(i, this.rows[i]);
        }
        return this;
    }

    public static Matrix getAddition(Matrix matrix, Matrix matrix1) {
        Matrix matrix2 = new Matrix(matrix);
        matrix2.addition(matrix1);

        return matrix2;
    }

    public static Matrix getSubtraction(Matrix matrix, Matrix matrix1) {
        Matrix matrix2 = new Matrix(matrix);
        matrix2.subtraction(matrix1);

        return matrix2;
    }


    public static Matrix getMultiplication(Matrix matrix, Matrix matrix1) {
        if (matrix.rows[0].getSize() != matrix1.rows.length) {
            throw new RuntimeException("Недопустимый размер матрицы");
        }
        Matrix multiplication = new Matrix(matrix.rows.length, matrix.rows[0].getSize());
        for (int i = 0; i < matrix.rows.length; i++) {
            multiplication.setRow(i, matrix.multiplyVector(matrix1.getColumn(i)));
        }
        return multiplication.transposition();
    }


    public String toString() {
        return Arrays.deepToString(rows).replace("[", "{").replace("]", "}");
    }

}

