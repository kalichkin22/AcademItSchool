package ru.academits.kalichkin.minesweeper.common;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScores {

    public static ArrayList<PersonWin> readScores() {
        ArrayList<PersonWin> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("scores.txt"))) {
            while (scanner.hasNext()) {
                PersonWin person = new PersonWin(scanner.next(), scanner.next());
                list.add(person);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден%n", "scores.txt");
        }
        return list;
    }

    public static void writeScores(String name, String time) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream("scores.txt", true))) {
            PersonWin person = new PersonWin(name, time);
            writer.print(person);
            writer.print(System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.printf("Файл %s не найден%n", "scores.txt");
        }
    }
}
