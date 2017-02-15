package ru.academits.kalichkin.cft;

import java.io.*;
import java.util.Scanner;


public class Cft {

    public static void main(String[] args) {

        if (args.length > 0) {
            if (args[0].equals("in.txt out.txt -i -a")) {
                try (Scanner scanner = new Scanner(new FileInputStream("in.txt"));
                     PrintWriter writer = new PrintWriter("out.txt")) {

                    int count = scanner.nextInt();
                    int[] array = new int[count];

                    while (scanner.hasNextInt()) {
                        for (int i = 0; i < array.length; ++i) {
                            array[i] = scanner.nextInt();
                        }
                    }

                    insertionSort(array);
                    for (int e : array) {
                        writer.println(e);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            } else if (args[1].equals("in.txt out.txt -i -d")) {
                try (Scanner scanner = new Scanner(new FileInputStream("in.txt"));
                     PrintWriter writer = new PrintWriter("out.txt")) {

                    int count = scanner.nextInt();
                    int[] array = new int[count];

                    while (scanner.hasNextInt()) {
                        for (int i = 0; i < array.length; ++i) {
                            array[i] = scanner.nextInt();
                        }
                    }

                    insertSort(array);
                    for (int e : array) {
                        writer.println(e);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            } else if (args[2].equals("in.txt out.txt -s -a")) {
                try (Scanner scanner = new Scanner(new FileInputStream("in.txt"));
                     PrintWriter writer = new PrintWriter("out.txt")) {

                    int count = scanner.nextInt();
                    int[] array = new int[count];

                    while (scanner.hasNextInt()) {
                        for (int i = 0; i < array.length; ++i) {
                            array[i] = scanner.nextInt();
                        }
                    }

                    insertSort(array);
                    for (int e : array) {
                        writer.println(e);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            }
        } else {
            System.out.println("Такой команды нет");
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

    public static void insertSort(int[] array) {

        for (int i = 1; i < array.length; i++) {

            int a = array[i];
            int b = i - 1;

            while (b >= 0 && array[b] <= a) {
                array[b + 1] = array[b];
                b--;
            }
            array[b + 1] = a;
        }
    }
}

