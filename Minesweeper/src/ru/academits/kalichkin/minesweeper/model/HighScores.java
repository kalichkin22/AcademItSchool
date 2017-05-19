package ru.academits.kalichkin.minesweeper.model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class HighScores {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public ArrayList<PersonWin> readScores(String filename) throws FileNotFoundException {
        ArrayList<PersonWin> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            while (scanner.hasNext()) {
                PersonWin person = new PersonWin(scanner.next(), scanner.next());
                list.add(person);
            }
        }
        list.sort(Comparator.comparing(PersonWin::getTime));
        return list;
    }

    public void writeScores(String filename, String name, String time) throws FileNotFoundException {
        this.fileName = filename;
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename, true))) {
            PersonWin person = new PersonWin(name, time);
            writer.println(person);
        }
    }
}
