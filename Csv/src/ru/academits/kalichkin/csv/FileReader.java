package ru.academits.kalichkin.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileReader {
    static List<String> readFile(String fileName) {
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

    static void writeFile(StringBuilder sb, String fileNameOut) {
        try (PrintWriter writer = new PrintWriter(fileNameOut)) {
            writer.print(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден%n", fileNameOut);
        }
    }
}




