package ru.academits.kalichkin.cft;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Cft {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"));
             PrintWriter writer = new PrintWriter("output.txt")) {
            while (scanner.hasNextInt()) {
                int[] array = new int[6];
                for (int i = 0; i < array.length; i++) {
                    array[i] = scanner.nextInt();
                }
                insertionSort(array);
                for (int e : array) {
                    writer.println(e);
                }
            }
        }
    }

    public static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {

            int a = array[i];
            int b = i - 1;

            while (b >= 0 && array[b] > a) {
                array[b + 1] = array[b];
                b--;
            }
            array[b + 1] = a;
        }
    }
}

