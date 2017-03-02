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
            int lengthColumn = array[0].length;
            if (array[i].length != lengthColumn) {
                throw new RuntimeException("Недопустимая длинна массива");
            } else {
                this.rows[i] = new Vector(array[i]);
            }
        }
    }


    public Matrix(Vector[] vectors) {
        this.rows = new Vector[vectors.length];
        for (int i = 0; i < this.rows.length; ++i) {
            int lengthColumn = vectors[0].getSize();
            if (vectors[i].getSize() != lengthColumn) {
                throw new RuntimeException("Недопустимая длинна вектора");
            } else {
                this.rows[i] = new Vector(vectors[i]);
            }
        }
    }

    public int getRowsLength() {
        return rows.length;
    }

    public int getColumnsLength() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index >= this.rows.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы массива1");
        } else {
            return new Vector(rows[index]);
        }
    }

    public void setRow(int index, Vector vector) {
        if (index >= this.rows.length || index < 0 || vector.getSize() != this.rows[0].getSize()) {
            throw new RuntimeException("Выход за границы массива2");
        } else {
            this.rows[index] = new Vector(vector);
        }
    }

    public Vector getColumn(int index) {
        if (index >= this.rows[0].getSize() || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Выход за границы массива3");
        } else {
            Vector column = new Vector(this.rows.length);
            for (int i = 0; i < this.rows.length; ++i) {
                column.setElement(i, this.rows[i].getElement(index));
            }
            return column;
        }
    }

    public Matrix transposition() {
        Matrix transposition = new Matrix(this.rows.length, this.rows[0].getSize());
        for (int i = 0; i < this.rows[0].getSize(); i++) {
            transposition.setRow(i, this.getColumn(i));
        }
        return transposition;
    }

    public Matrix transposition2() {
        Matrix transposition = new Matrix(this);
        for (int i = 0; i < this.rows[0].getSize(); i++) {
            this.rows[i] = transposition.getColumn(i);
        }
        return this;
    }

    public Matrix multiplicationByScalar(double number) {
        for (Vector row : this.rows) {
            row.multiplicationByScalar(number);
        }
        return this;
    }


    public Vector multiplyVector(Vector vector) {
        if (vector.getSize() != this.rows[0].getSize()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
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
    }


    public Matrix addition(Matrix matrix) {
        if (this.rows.length != matrix.rows.length
                || this.rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
            for (int i = 0; i < this.rows.length; i++) {
                setRow(i, this.rows[i].addition(matrix.rows[i]));
            }
            return this;
        }
    }

    public Matrix subtraction(Matrix matrix) {
        if (this.rows.length != matrix.rows.length
                || this.rows[0].getSize() != matrix.rows[0].getSize()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
            for (int i = 0; i < this.rows.length; i++) {
                setRow(i, this.rows[i].subtraction(matrix.rows[i]));
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
        if (matrix.getColumnsLength() != matrix1.getRowsLength()) {
            throw new RuntimeException("Недопустимый размер матрицы");
        } else {
            Matrix multiplication = new Matrix(matrix.rows.length, matrix.rows[0].getSize());
            for (int i = 0; i < matrix.rows.length; i++) {
                multiplication.setRow(i, matrix.multiplyVector(matrix1.getColumn(i)));
            }
            return multiplication.transposition();
        }
    }


    public String toString() {
        return Arrays.deepToString(rows).replace("[", "{").replace("]", "}");
    }
}
