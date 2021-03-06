package ru.academits.kalichkin.matrix.matrix;

import ru.academits.kalichkin.vector.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int columns, int rows) {
        this.rows = new Vector[rows];
        for (int i = 0; i < this.rows.length; ++i) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.rows);
    }

    public Matrix(double[][] array) {
        this.rows = new Vector[array.length];
        for (int i = 0; i < array.length; ++i) {
            if (array[i].length != array[0].length) {
                throw new RuntimeException("Недопустимая длинна массива");
            } else {
                this.rows[i] = new Vector(array[i]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        this.rows = new Vector[vectors.length];
        for (int i = 0; i < this.rows.length; ++i) {
            if (vectors[i].getSize() != vectors[0].getSize()) {
                throw new RuntimeException("Недопустимая длинна вектора");
            } else {
                this.rows[i] = new Vector(vectors[i]);
            }
        }
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index >= this.rows.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы матрицы");
        } else {
            return new Vector(rows[index]);
        }
    }

    public void setRow(int index, Vector vector) {
        if (index >= this.rows.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы матрицы");
        } else if (vector.getSize() != this.getColumnsCount()) {
            throw new RuntimeException("Выход за границы матрицы");
        } else {
            this.rows[index] = new Vector(vector);
        }
    }

    public Vector getColumn(int index) {
        if (index >= this.getColumnsCount() || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы матрицы");
        } else {
            Vector column = new Vector(this.rows.length);
            for (int i = 0; i < this.rows.length; ++i) {
                column.setElement(i, this.rows[i].getElement(index));
            }
            return column;
        }
    }

    public Matrix transposition() {
        Vector[] transposition = new Vector[this.getColumnsCount()];
        for (int i = 0; i < this.getColumnsCount(); i++) {
            transposition[i] = this.getColumn(i);
        }
        this.rows = transposition;
        return this;
    }

    public Matrix multiplicationByScalar(double number) {
        for (Vector row : this.rows) {
            row.multiplicationByScalar(number);
        }
        return this;
    }

    public double determinant() {
        if (this.rows.length != this.getColumnsCount()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        }
        if (this.getColumnsCount() == 1) {
            return this.rows[0].getElement(0);
        } else if (this.getColumnsCount() == 2) {
            return this.rows[0].getElement(0) * this.rows[1].getElement(1)
                    - this.rows[1].getElement(0) * this.rows[0].getElement(1);
        }

        double determinant = 0;
        for (int i = 0; i < this.getColumnsCount(); i++) {
            Matrix minor = new Matrix(0, this.getColumnsCount() - 1);
            for (int j = 0; j < this.getColumnsCount() - 1; j++) {
                minor.rows[j] = new Vector(this.getColumnsCount() - 1);
            }
            for (int k = 1; k < this.getColumnsCount(); k++) {
                int count = 0;
                for (int l = 0; l < this.getColumnsCount(); l++) {
                    if (l == i) {
                        continue;
                    }
                    minor.rows[k - 1].setElement(count, this.rows[k].getElement(l));
                    count++;
                }
            }
            determinant += Math.pow(-1.0, 1.0 + i + 1.0) * this.rows[0].getElement(i)
                    * minor.determinant();
        }
        return determinant;
    }

    public String toString() {
        return Arrays.deepToString(rows).replace("[", "{").replace("]", "}");
    }


    public Vector multiplyVector(Vector vector) {
        if (vector.getSize() != this.getColumnsCount()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
            Vector multiply = new Vector(this.rows.length);
            for (int i = 0; i < this.rows.length; i++) {
                double sum = 0;
                for (int j = 0; j < this.getColumnsCount(); j++) {
                    sum += this.getRow(i).getElement(j) * vector.getElement(j);
                }
                multiply.setElement(i, sum);
            }
            return multiply;
        }
    }

    public Matrix addition(Matrix matrix) {
        if (this.rows.length != matrix.rows.length
                || this.getColumnsCount() != matrix.getColumnsCount()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
            for (int i = 0; i < this.rows.length; i++) {
                this.rows[i].addition(matrix.rows[i]);
            }
            return this;
        }
    }

    public Matrix subtraction(Matrix matrix) {
        if (this.rows.length != matrix.rows.length
                || this.getColumnsCount() != matrix.getColumnsCount()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
            for (int i = 0; i < this.rows.length; i++) {
                this.rows[i].subtraction(matrix.rows[i]);
            }
            return this;
        }
    }

    public static Matrix getAddition(Matrix matrix, Matrix matrix1) {
        Matrix sum = new Matrix(matrix);
        sum.addition(matrix1);

        return sum;
    }

    public static Matrix getSubtraction(Matrix matrix, Matrix matrix1) {
        Matrix difference = new Matrix(matrix);
        difference.subtraction(matrix1);

        return difference;
    }

    public static Matrix getMultiplication(Matrix matrix, Matrix matrix1) {
        if (matrix1.rows.length != matrix.getColumnsCount()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
            Matrix multiplication = new Matrix(matrix.rows.length, matrix1.getColumnsCount());
            for (int i = 0; i < matrix1.getColumnsCount(); i++) {
                multiplication.setRow(i, matrix.multiplyVector(matrix1.getColumn(i)));
            }
            return multiplication.transposition();
        }
    }
}
