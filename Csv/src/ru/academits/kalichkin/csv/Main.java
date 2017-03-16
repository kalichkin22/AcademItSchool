package ru.academits.kalichkin.csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(new FileInputStream(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            List<String> list = new ArrayList<>();

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            StringBuilder table = new StringBuilder();
            table.append("<html>").append(System.lineSeparator());
            table.append(" <head>").append(System.lineSeparator());
            table.append("  <meta charset = \"utf-8\">").append(System.lineSeparator());
            table.append("  <title> Разбор формата CSV </title>").append(System.lineSeparator());
            table.append(" </head>").append(System.lineSeparator());
            table.append(" <body>").append(System.lineSeparator());
            table.append("  <table bordercolor=\"black\" border=\"1\" width=\"80%\">").append(System.lineSeparator());

            for (String e : list) {
                table.append("<tr><td>");
                int i = 0;
                for (int j = 0; j < e.length(); j++) {
                    char c = e.charAt(j);
                    e = e.replace(",", "</td>");
                    if (c == '"') {
                        if (j > i) {
                            table.append(e.substring(i, j));
                        }
                        i = j + 1;
                    }
                }
                if (i <= e.length()) {
                    table.append("<td>");
                    table.append(e.substring(i));
                }

                table.append("</td></tr><br/>").append(System.lineSeparator());
                if (e.contains("\"")) {
                    //TODO если в ячейке с ковычками то не писать тд и тр
                }
            }


            table.append("  </table>").append(System.lineSeparator());
            table.append(" </body>").append(System.lineSeparator());
            table.append("</html>").append(System.lineSeparator());
            writer.print(table);
        }
    }
}



