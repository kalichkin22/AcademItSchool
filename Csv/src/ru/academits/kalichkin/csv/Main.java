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
    private static final String ROW = "<tr><td>";
    private static final String BREAK = "</td></tr><br/>";
    private static final String DETAIL = "</td><td>";
    private static final char SEPARATOR = ',';
    private static final char QUOTE = '"';


    public static void main(String[] args) {
        List<String> list = FileReader.readFile(args[0]);
        StringBuilder table = new StringBuilder();

        table.append(HTML).append(HEAD).append(META).append(TITLE).append(HEADCLOSE).append(BODY).append(TABLE).append(System.lineSeparator());

        boolean isQuoted = false;
        int quoteCount = 0;

        for (String e : list) {
            if (!isQuoted) {
                table.append(ROW);
            }
            if (e.contains("<")) {
                e = e.replace("<", "&lt");
            }
            if (e.contains(">")) {
                e = e.replace(">", "&gt");
            }

            for (int i = 0; i < e.length(); ++i) {
                char c = e.charAt(i);
                if (c == QUOTE) {
                    if (!isQuoted) {
                        isQuoted = true;
                        continue;
                    } else {
                        ++quoteCount;
                        if (quoteCount % 2 == 0) {
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
                    if (c == SEPARATOR) {
                        if (i == e.length() - 1) {
                            table.append(BREAK).append(System.lineSeparator());
                            continue;
                        }
                        table.append(DETAIL);
                        continue;
                    }
                }
                table.append(c);

                if (!isQuoted && i == e.length() - 1) {
                    table.append(BREAK).append(System.lineSeparator());
                }
            }
        }
        table.append(TABLECLOSE).append(BODYCLOSE).append(HTMLCLOSE);
        FileReader.writeFile(table, args[1]);
    }
}

