import java.util.Arrays;


public class Vector {
    private int n;
    private double[] vector;


    public Vector(int n) {
        this.n = n;
        this.vector = new double[n];
    }

    public Vector(Vector vector) {
        this(vector.getVector());
    }

    public Vector(double... vector) {
        this.vector = vector;
    }

    public Vector(int n, double[] vector) {
        this.n = n;
        double[] array = new double[n];
        if (vector.length < n) {
            this.vector = Arrays.copyOf(vector, n);
        } else {
            this.vector = vector;
        }

    }


    public double[] getVector() {
        return vector;
    }

    public String toString() {
        return Arrays.toString(vector);
    }
}
