package ru.academits.kalichkin.csv;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] line = new String[3];
        try (Scanner scanner = new Scanner(new FileInputStream("csv.txt"));
             PrintWriter writer = new PrintWriter("html.txt")) {

            for (int i = 0; i < line.length; ++i) {
                line[i] = scanner.nextLine();
            }

            for (String e : line) {
                writer.println(e);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}