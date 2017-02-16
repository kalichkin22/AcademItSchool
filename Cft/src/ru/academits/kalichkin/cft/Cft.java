package ru.academits.kalichkin.cft;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Cft {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();


        try {
            try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
                 PrintWriter writer = new PrintWriter(args[1])) {
                while (scanner.hasNext()) {
                    lines.add(scanner.nextLine());
                }
                String[] strings = lines.toArray(new String[lines.size()]);

                if (args.length > 0) {
                    if (args[2].equals("-i") && args[3].equals("-a")) {
                        int numbers[] = new int[strings.length];
                        for (int i = 0; i < strings.length; i++) {
                            numbers[i] = Integer.parseInt(strings[i]);
                        }
                        insertionSort(numbers);
                        for (int e : numbers) {
                            writer.println(e);
                        }
                    } else if (args[2].equals("-i") && args[3].equals("-d")) {
                        int numbers[] = new int[strings.length];
                        for (int i = 0; i < strings.length; i++) {
                            numbers[i] = Integer.parseInt(strings[i]);
                        }
                        insertSort(numbers);
                        for (int e : numbers) {
                            writer.println(e);
                        }
                    } else if (args[2].equals("-s") && args[3].equals("-a")) {
                        insertionStringSort(strings);
                        for (String e : strings) {
                            writer.println(e);
                        }
                    } else if (args[2].equals("-s") && args[3].equals("-d")) {
                        insertStringSort(strings);
                        for (String e : strings) {
                            writer.println(e);
                        }

                    } else {
                        System.out.println("Такой команды нет");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Обработка команды невозможна, файл содержит не только целые числа");
            } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                System.out.println("Введите команду полностью");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }

    }


    private static void insertionSort(int[] array) {

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

    private static void insertSort(int[] array) {

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

    private static void insertionStringSort(String[] array) {

        for (int i = 1; i < array.length; i++) {

            String a = array[i];
            int b = i - 1;

            while (b >= 0 && array[b].compareToIgnoreCase(a) > 0) {
                array[b + 1] = array[b];
                b--;
            }
            array[b + 1] = a;
        }
    }

    private static void insertStringSort(String[] array) {

        for (int i = 1; i < array.length; i++) {

            String a = array[i];
            int b = i - 1;

            while (b >= 0 && array[b].compareToIgnoreCase(a) < 0) {
                array[b + 1] = array[b];
                b--;
            }
            array[b + 1] = a;
        }
    }
}

