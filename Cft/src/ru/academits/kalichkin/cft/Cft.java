package ru.academits.kalichkin.cft;

import java.io.*;
import java.util.Scanner;


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

            while (scanner.hasNextLine()) {
                for (int i = 0; i < args.length; i++) {
                    args[i] = scanner.nextLine();
                }
                insertionSort(args);
                for (String e : args) {
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

    public static void insertionSort(String[] strings) {
        for (int j = 0; j < strings.length; j++) {
            for (int i = j + 1; i < strings.length; i++) {
                if (strings[i].compareTo(strings[j]) < 0) {
                    String temp = strings[j];
                    strings[j] = strings[i];
                    strings[i] = temp;
                }
            }
        }
    }
}

