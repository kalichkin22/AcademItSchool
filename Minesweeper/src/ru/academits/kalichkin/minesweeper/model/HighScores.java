package ru.academits.kalichkin.minesweeper.model;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class HighScores {
    private String filename;

    public HighScores(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public ArrayList<PersonWin> readScores() throws FileNotFoundException {
        ArrayList<PersonWin> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(filename))) {
            while (scanner.hasNext()) {
                PersonWin person = new PersonWin(scanner.next(), scanner.next());
                list.add(person);
            }
        }
        Comparator<PersonWin> comparator = new SortPerson();
        list.sort(comparator);
        return list;
    }

    public void writeScores(String name, String time) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(filename, true))) {
            PersonWin person = new PersonWin(name, time);
            writer.print(person);
            writer.print(System.lineSeparator());
        }
    }
}
