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

                for (int j = 0; j < e.length() - 1; ++j) {
                    char c = e.charAt(j);

                    if (c == '"') {
                        ++quote;
                        if (!isQuoted) {
                            isQuoted = true;
                            continue;
                        } else {
                            if (c == e.charAt(j + 1)) {
                                table.append(c);
                            }
                            isQuoted = false;
                            continue;
                        }
                    }
                    if (!isQuoted) {
                        if (c == ',') {
                            table.append("</td><td>");
                            continue;
                        }
                    }
                    table.append(c);
                }

                if (!isQuoted) {
                    table.append("</td></tr><br/>").append(System.lineSeparator());
                }
            }

            table.append("  </table>").append(System.lineSeparator());
            table.append(" </body>").append(System.lineSeparator());
            table.append("</html>").append(System.lineSeparator());

            if (quote % 2 != 0) {
                System.out.println("Возможно вы ввели лишнюю кавычку");
            }
            writer.print(table);
        }
    }
}
