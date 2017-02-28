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
                for (Vector row : this.rows) {
                    column.setElement(i, this.rows[i].getElement(index));
                }
            }
            return column;
        }
    }

    public Matrix transposition() {
        Matrix transposition = new Matrix(this.rows[0].getSize(), this.rows.length);
        for (int i = 0; i < this.rows[0].getSize(); i++) {
            for (Vector row : this.rows) {
                transposition.setRow(i, this.getColumn(i));
            }
        }
        return transposition;
    }

    public Matrix multiplicationByScalar(double number) {
        for (int i = 0; i < this.rows.length; ++i) {
            this.rows[i] = this.rows[i].multiplicationByScalar(number);
        }
        return this;
    }

    public double determinant() {
        double determinant = 0;
        double d1 = 0;
        double d2 = 0;
        for (int i = 0; i < this.rows.length; i++) {
            for (int j = i+1; j < this.rows[0].getSize(); j++) {
                d1 += (this.getRow(i).getElement(i) * this.getColumn(j).getElement(j));
            }
        }
        return d1;
    }


    public String toString() {
        return Arrays.deepToString(rows).replace("[", "{").replace("]", "}");
    }

}

