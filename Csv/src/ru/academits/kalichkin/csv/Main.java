
package ru.academits.kalichkin.csv;

import ru.academits.kalichkin.csv.line.Line;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Line> lineList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {

            String line = null;
            Scanner scanner = null;

            int index = 0;
            while ((line = reader.readLine()) != null) {
                scanner = new Scanner(line);
                Line cell = new Line();
                scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    if (scanner.hasNextLine() && data.contains("\"")) {
                        data = data.replace(System.lineSeparator(), "");
                    }
                    if (index == 0) {
                        cell.setFirstCell(data);
                    } else if (index == 1) {
                        cell.setSecondCell(data);
                    } else if (index == 2) {
                        cell.setThirdCell(data);
                    } else {
                        System.out.println("Некорректные данные: " + data);
                    }
                    index++;
                }
                index = 0;
                lineList.add(cell);
            }

            writer.print(lineList.toString().replace("[", "<table>").replace("]", "").replace(",", "")
                    + System.lineSeparator() + "</table>");
        }
    }
}



