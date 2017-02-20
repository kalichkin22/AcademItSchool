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

    public Vector additionVector(Vector vector) {
        int length;
        if (this.getSize() >= vector.getSize()) {
            length = this.getSize();
        } else {
            length = vector.getSize();
        }
        for (int i = 0; i < length; ++i) {
            if (i >= this.getSize() || i >= vector.getSize()) {
                break;
            }
            this.setElem(i, this.getElem(i) + vector.getElem(i));
        }
        return this;
    }

    public Vector subtractionVector(Vector vector) {
        int length;
        if (this.getSize() > vector.getSize()) {
            length = this.getSize();
        } else {
            length = vector.getSize();
        }
        for (int i = 0; i < length; ++i) {
            if (i >= this.getSize() || i >= vector.getSize()) {
                break;
            }
            this.setElem(i, this.getElem(i) - vector.getElem(i));
        }
        return this;
    }

    public Vector multiplicationVectorByScalar(double number) {
        for (int i = 0; i < this.getSize(); ++i) {
            this.setElem(i, this.getElem(i) * number);
        }
        return this;
    }

    public Vector reverseVector() {
        for (int i = 0; i < this.getSize(); ++i) {
            if (this.getElem(i) == 0) {
                break;
            }
            this.setElem(i, this.getElem(i) * -1);
        }
        return this;
    }

    public double getVectorLength() {
        double length = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] == 0) {
                break;
            }
            length = +(Math.sqrt(Math.pow((vector[i]), 2)));
        }
        return length;
    }

    public boolean equals(Object o) {

        if (!(o instanceof Vector)) {
            return false;
        }

        Vector v = (Vector) o;

        double epsilon = 0.00001;

        for (int i = 0; i < this.getSize(); ++i) {
            if (this.getSize() == v.getSize() && Math.abs(this.getElem(i) - v.getElem(i)) <= epsilon) {
                return true;
            }
        }
        return false;
    }


    public int hashCode() {
        final int prime = 5;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(vector);
        return hash;
    }

    public static Vector getAdditionVector(Vector vector, Vector vector1) {
        int length;
        if (vector.getSize() >= vector1.getSize()) {
            length = vector.getSize();
        } else {
            length = vector1.getSize();
        }
        Vector vector2 = new Vector(length);

        for (int i = 0; i < vector2.getSize(); ++i) {
            if (i >= vector.getSize() || i >= vector1.getSize()) {
                break;
            }
            vector2.setElem(i, (vector.getElem(i) + vector1.getElem(i)));
        }
        return vector2;
    }

    public static Vector getSubtractionVector(Vector vector, Vector vector1) {
        int length;
        if (vector.getSize() > vector1.getSize()) {
            length = vector.getSize();
        } else {
            length = vector1.getSize();
        }
        Vector vector2 = new Vector();
        System.out.println(vector2);
        for (int i = 0; i < vector2.getSize(); ++i) {
            if (i >= vector.getSize() || i >= vector1.getSize()) {
                break;
            }
            vector2.setElem(i, (vector.getElem(i) - vector1.getElem(i)));
        }
        return vector2;
    }

}
