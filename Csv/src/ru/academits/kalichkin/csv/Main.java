package ru.academits.kalichkin.csv;

import java.util.List;

public class Main {
    private static final String HTML = "<html>";
    private static final String HTMLCLOSE = "</html>";
    private static final String HEAD = "<head>";
    private static final String HEADCLOSE = "</head>";
    private static final String META = "<meta charset = \"utf-8\">";
    private static final String TITLE = "<title> Разбор формата CSV </title>";
    private static final String BODY = "<body>";
    private static final String BODYCLOSE = "</body>";
    private static final String TABLE = "<table bordercolor=\"black\" border=\"1\" width=\"80%\">";
    private static final String TABLECLOSE = "</table>";
    private static final String TABLEROW = "<tr><td>";
    private static final String BREAK = "</td></tr><br/>";
    private static final String TABLEDETAIL = "</td><td>";


    public static void main(String[] args) {
        List<String> list = FileReader.readFile(args[0]);
        StringBuilder table = new StringBuilder();

        table.append(HTML).append(System.lineSeparator());
        table.append(HEAD).append(System.lineSeparator());
        table.append(META).append(System.lineSeparator());
        table.append(TITLE).append(System.lineSeparator());
        table.append(HEADCLOSE).append(System.lineSeparator());
        table.append(BODY).append(System.lineSeparator());
        table.append(TABLE).append(System.lineSeparator());

        boolean isQuoted = false;
        int quote = 0;
        for (String e : list) {
            if (!isQuoted) {
                table.append(TABLEROW);
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
                            table.append(BREAK).append(System.lineSeparator());
                        }
                        isQuoted = false;
                        continue;
                    }
                }

                if (!isQuoted) {
                    if (c == ',') {
                        if (i == e.length() - 1) {
                            table.append(BREAK).append(System.lineSeparator());
                            continue;
                        }
                        table.append(TABLEDETAIL);
                        continue;
                    }
                }
                table.append(c);

                if (!isQuoted && i == e.length() - 1) {
                    table.append(BREAK).append(System.lineSeparator());
                }
            }
        }
        table.append(TABLECLOSE).append(System.lineSeparator());
        table.append(BODYCLOSE).append(System.lineSeparator());
        table.append(HTMLCLOSE).append(System.lineSeparator());

        FileReader.writeFile(table, args[1]);
    }
}

