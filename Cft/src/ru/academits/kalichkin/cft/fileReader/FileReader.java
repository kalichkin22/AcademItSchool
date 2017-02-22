package ru.academits.kalichkin.cft.fileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FileReader extends ArrayList implements List {


    public static ArrayList<String> read(String fileName) {
        ArrayList <String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(fileName))) {

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден\n", fileName);
        }
        return list;
    }

    public static void writeFile(ArrayList<String> list, String fileNameOut) {
        try (PrintWriter writer = new PrintWriter(fileNameOut)) {
            for (String e : list) {
                writer.println(e);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден\n", fileNameOut);
        }
    }



}





