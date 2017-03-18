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
            StringBuilder table = new StringBuilder();

            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }

            table.append("<html>").append(System.lineSeparator());
            table.append(" <head>").append(System.lineSeparator());
            table.append("  <meta charset = \"utf-8\">").append(System.lineSeparator());
            table.append("  <title> Разбор формата CSV </title>").append(System.lineSeparator());
            table.append(" </head>").append(System.lineSeparator());
            table.append(" <body>").append(System.lineSeparator());
            table.append("  <table bordercolor=\"black\" border=\"1\" width=\"80%\">").append(System.lineSeparator());

            boolean isQuoted = false;
            int quote = 0;
            for (String e : list) {
                if (!isQuoted) {
                    table.append("<tr><td>");
                }

                for (int i = 0; i < e.length(); ++i) {
                    char c = e.charAt(i);
                    if (c == '"') {
                        if (!isQuoted) {

                            isQuoted = true;
                            continue;
                        } else {
                            ++quote;
                            if (quote % 2 == 0) {
                                table.append(c);
                            }
                            if (i == e.length() - 1) {
                                table.append("</td></tr><br/>").append(System.lineSeparator());
                            }
                            isQuoted = false;
                            continue;
                        }
                    }

                    if (!isQuoted) {
                        if (c == ',') {
                            if (i == e.length() - 1) {
                                table.append("</td></tr><br/>").append(System.lineSeparator());
                                continue;
                            }
                            table.append("</td><td>");
                            continue;
                        }
                    }

                    table.append(c);
                }
            }

            table.append("  </table>").append(System.lineSeparator());
            table.append(" </body>").append(System.lineSeparator());
            table.append("</html>").append(System.lineSeparator());

            writer.print(table);
        }
    }
}
