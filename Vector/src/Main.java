

public class Main {
    public static void main(String[] args) {
        double[] array = {1, 2, 3, 4};

        Vector vector = new Vector(1, 2, 3, 4, 5);
        System.out.println(vector);

        Vector vector1 = new Vector(vector);
        System.out.println(vector1);

        Vector vector2 = new Vector(10);
        System.out.println(vector2);

        Vector vector3 = new Vector(7, array);
        System.out.println(vector3);
    }
}
