package Vector;

import java.util.Arrays;


public class Vector {
    private double[] vector;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
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
            if (vector.length < n) {
                this.vector = Arrays.copyOf(vector, n);
            } else {
                this.vector = Arrays.copyOf(vector, vector.length);
            }
        }
    }

    public double getElem(int index) {
        return vector[index];
    }

    public void setElem(int index, double value) {
        vector[index] = value;
    }

    public int getSize() {
        return vector.length;
    }

    public String toString() {
        return Arrays.toString(vector).replace("[", "{").replace("]", "}");
    }

    public Vector addition(Vector vector) {
        int length = Math.max(this.getSize(), vector.getSize());
        if (this.getSize() < vector.getSize()) {
            double[] old = this.vector;
            this.vector = new double[length];
            System.arraycopy(old, 0, this.vector, 0, old.length);
        }

        for (int i = 0; i < length; ++i) {
            if (i >= vector.getSize()) {
                break;
            }
            this.setElem(i, this.getElem(i) + vector.getElem(i));
        }
        return this;
    }


    public Vector subtraction(Vector vector) {
        int length = Math.max(this.getSize(), vector.getSize());
        if (this.getSize() < vector.getSize()) {
            double[] old = this.vector;
            this.vector = new double[length];
            System.arraycopy(old, 0, this.vector, 0, old.length);
        }

        for (int i = 0; i < length; ++i) {
            if (i >= vector.getSize()) {
                break;
            }
            this.setElem(i, this.getElem(i) - vector.getElem(i));
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
            length = Math.sqrt(e);
        }
        return Math.pow(length, 2);
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

        for (int i = 0; i < this.getSize(); ++i) {
            if (this.getElem(i) != v.getElem(i)) {
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
        return vector.addition(vector1);
    }

    public static Vector getSubtraction(Vector vector, Vector vector1) {
        return vector.subtraction(vector1);
    }

    public static Double getScalarSum(Vector vector, Vector vector1) {
        int length = Math.min(vector.getSize(), vector1.getSize());
        double sum = 0;

        for (int i = 0; i < length; ++i) {
            if (i >= length) {
                break;
            }
            sum = sum + (vector.getElem(i) * vector1.getElem(i));
        }

        return sum;
    }
}


