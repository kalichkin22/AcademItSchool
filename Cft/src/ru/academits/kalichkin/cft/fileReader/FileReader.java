package ru.academits.kalichkin.cft.fileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FileReader {

    public static ArrayList<String> readString(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден%n", fileName);
        }
        return list;
    }

    public static ArrayList<Integer> readInt(String fileName) {
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {

            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден%n", fileName);
        }
        return list;
    }


    public static <T> void writeFile(List<T> list, String fileNameOut) {
        try (PrintWriter writer = new PrintWriter(fileNameOut)) {
            for (T e : list) {
                writer.println(e);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден%n", fileNameOut);
        }
    }
}