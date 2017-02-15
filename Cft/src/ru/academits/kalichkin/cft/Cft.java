package ru.academits.kalichkin.cft;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Cft {

    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            String[] strings = lines.toArray(new String[lines.size()]);

            int numbers[] = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                numbers[i] = Integer.parseInt(strings[i]);
            }

            if (args.length > 0) {
                if (args[0].equals("in.txt") && args[1].equals("out.txt") && args[2].equals("-i") && args[3].equals("-a")) {
                    insertionSort(numbers);
                    for (int e : numbers) {
                        writer.println(e);
                    }
                } else if (args[0].equals("in.txt") && args[1].equals("out.txt") && args[2].equals("-i") && args[3].equals("-d")) {
                    insertSort(numbers);
                    for (int e : numbers) {
                        writer.println(e);
                    }
                } else if (args[0].equals("in.txt") && args[1].equals("out.txt") && args[2].equals("-s") && args[3].equals("-a")) {

                }

            } else {
                System.out.println("Такой команды нет");
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

