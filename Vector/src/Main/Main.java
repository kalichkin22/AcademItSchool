package Main;

import Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {1, 2, 3, 4};

        Vector vector = new Vector(1, 2, 3, 4);
        System.out.println("Вектор " + vector);

        Vector vector1 = new Vector(array);
        System.out.println("Вектор1 " + vector1);

        Vector vector2 = new Vector(4);
        System.out.println("Вектор2 " + vector2);

        Vector vector3 = new Vector(6, array);
        System.out.println("Вектор3 " + vector3);

        //System.out.println("Размерность вектора:" + vector3.getSize());

        //System.out.printf("Умножение вектора на скаляр: %s\n", vector3.multiplicationByScalar(2));
        //System.out.printf("Разворот вектора: %s\n", vector.reverse());
        //System.out.printf("Длина вектора: %s\n", vector.getLength());

        //System.out.println(vector.equals(vector1));
        //System.out.println(vector2.hashCode());

        //System.out.printf("Прибавление к вектору другого вектора: %s\n", vector3.addition(vector));
        //System.out.printf("Вычитание из вектора другого вектора: %s\n", vector3.subtraction(vector1));
        //System.out.println(Vector.getAddition(vector, vector3));
        System.out.println(Vector.getSubtraction(vector, vector3));

       //System.out.println(Vector.getScalarMultiplication(vector3, vector));

    }
}
