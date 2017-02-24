package Vector;

import java.util.Arrays;


public class Vector {
    private double[] vector;

    public Vector(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Размерность вектора не должна быть меньше нуля");
        } else {
            this.vector = new double[n];
        }
    }

    public Vector(Vector vector) {
        this(vector.vector);

    }

    public Vector(double... vector) {
        this.vector = Arrays.copyOf(vector, vector.length);
    }

    public Vector(int n, double[] vector) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
        } else {
            this.vector = Arrays.copyOf(vector, n);

        }
    }

    public double getElement(int index) {
        return vector[index];
    }

    public void setElement(int index, double value) {
        vector[index] = value;
    }

    public int getSize() {
        return vector.length;
    }

    public String toString() {
        return Arrays.toString(vector).replace("[", "{").replace("]", "}");
    }

    public Vector addition(Vector vector) {
        int length = Math.max(this.vector.length, vector.vector.length);
        if (this.vector.length < vector.vector.length) {
            double[] old = this.vector;
            this.vector = new double[length];
            System.arraycopy(old, 0, this.vector, 0, old.length);
        }

        for (int i = 0; i < vector.vector.length; i++) {
            this.vector[i] = this.vector[i] + vector.vector[i];
        }
        return this;
    }


    public Vector subtraction(Vector vector) {
        int length = Math.max(this.vector.length, vector.vector.length);
        if (this.vector.length < vector.vector.length) {
            double[] old = this.vector;
            this.vector = new double[length];
            System.arraycopy(old, 0, this.vector, 0, old.length);
        }
        for (int i = 0; i < vector.vector.length; i++) {
            this.vector[i] = this.vector[i] - vector.vector[i];
        }
        return this;
    }


    public Vector multiplicationByScalar(double number) {
        for (int i = 0; i < this.vector.length; ++i) {
            this.vector[i] = this.vector[i] * number;
        }
        return this;
    }

    public Vector reverse() {
        return this.multiplicationByScalar(-1);
    }

    public double getLength() {
        double length = 0;
        for (double e : vector) {
            length += Math.pow(e, 2);
        }
        return Math.sqrt(length);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Vector v = (Vector) o;

        if (this.getSize() != v.getSize()) {
            return false;
        }

        for (int i = 0; i < this.vector.length; ++i) {
            if (this.vector[i] != v.vector[i]) {
                return false;
            }
        }
        return true;
    }


    public int hashCode() {
        final int prime = 5;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(vector);
        return hash;
    }

    public static Vector getAddition(Vector vector, Vector vector1) {
        Vector vector2 = new Vector(vector);
        vector2.addition(vector1);

        return vector2;
    }

    public static Vector getSubtraction(Vector vector, Vector vector1) {
        Vector vector2 = new Vector(vector);
        vector2.subtraction(vector1);

        return vector2;
    }

    public static double getScalarMultiplication(Vector vector, Vector vector1) {
        int length = Math.min(vector.getSize(), vector1.getSize());
        double multiplication = 0;

        for (int i = 0; i < length; i++) {
            multiplication += (vector.getElement(i) * vector1.getElement(i));
        }

        return multiplication;
    }
}


