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

        Vector vector3 = new Vector(8, array);
        System.out.println("Вектор3 " + vector3);

       /* System.out.println("Размерность вектора:" + vector3.getSize());

        System.out.printf("Умножение вектора на скаляр: %s\n", vector3.multiplicationVectorByScalar(2));
        System.out.printf("Разворот вектора: %s\n", vector.reverseVector());
        System.out.printf("Длина вектора: %s\n", vector.getVectorLength());

        System.out.println(vector.equals(vector1));
        System.out.println(vector1.hashCode());


        System.out.printf("Прибавление к вектору другого вектора: %s\n", vector.additionVector(vector3));
        System.out.printf("Вычитание из вектора другого вектора: %s\n", vector1.subtractionVector(vector3));*/
        System.out.println(Vector.getAdditionVector(vector1, vector3));


    }
}
