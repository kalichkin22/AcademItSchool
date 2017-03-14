package ru.academits.kalichkin.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            StringBuilder table = new StringBuilder();
            StringBuilder line = new StringBuilder();
            String cell = "";

            for (String e : list) {

                for (int i = 0; i < e.length(); i++) {
                    char c = e.charAt(i);
                    cell += c;
                }
                table.append(e).append("</td></tr><br/>").append(System.lineSeparator());
            }

            writer.print(table);
        }
    }
}



