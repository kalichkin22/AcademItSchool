package ru.academits.kalichkin.csv;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = FileReader.readFile(args[0]);
        StringBuilder table = new StringBuilder();

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

                if (!isQuoted && i == e.length() - 1) {
                    table.append("</td></tr><br/>").append(System.lineSeparator());
                }
            }
        }
        table.append("  </table>").append(System.lineSeparator());
        table.append(" </body>").append(System.lineSeparator());
        table.append("</html>").append(System.lineSeparator());

        FileReader.writeFile(table, args[1]);
    }
}

