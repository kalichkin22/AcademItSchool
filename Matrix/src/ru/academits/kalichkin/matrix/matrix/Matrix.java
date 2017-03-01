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

        for (int i = 0; i < this.rows[0].getSize(); ++i) {
            double elem = (this.getRow(0).getElement(0) * vector.getElement(0))
                    + (this.getRow(0).getElement(1) * vector.getElement(1))
                    + (this.getRow(0).getElement(2) * vector.getElement(2));
            double elem1 = (this.getRow(1).getElement(0) * vector.getElement(0))
                    + (this.getRow(1).getElement(1) * vector.getElement(1))
                    + (this.getRow(1).getElement(2) * vector.getElement(2));
            double elem2 = (this.getRow(2).getElement(0) * vector.getElement(0))
                    + (this.getRow(2).getElement(1) * vector.getElement(1))
                    + (this.getRow(2).getElement(2) * vector.getElement(2));

            multiply.setElement(0, elem);
            multiply.setElement(1, elem1);
            multiply.setElement(2, elem2);
        }
        return multiply;
    }


    public String toString() {
        return Arrays.deepToString(rows).replace("[", "{").replace("]", "}");
    }

}

