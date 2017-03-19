package ru.academits.kalichkin.csv;

import java.util.List;

public class Main {
    private static final String ROW = "<tr><td>";
    private static final String BREAK = "</td></tr><br/>";
    private static final String DETAIL = "</td><td>";
    private static final char SEPARATOR = ',';
    private static final char QUOTE = '"';


    public static void main(String[] args) {
        List<String> list = FileReader.readFile(args[0]);
        StringBuilder table = new StringBuilder();
        table.append(FormatInHtml.tableOpen());

        boolean isQuoted = false;
        int quoteCount = 0;
        int quoteCountAll = 0;

        for (String e : list) {
            if (!isQuoted) {
                table.append(ROW);
            }
            if (e.contains("&")) {
                e = e.replace("&", "&amp");
                if (e.contains("&amp")) {
                    e = e.replace("<", "&lt").replace(">", "&gt");
                }
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
                    ++quoteCountAll;
                    if (!isQuoted) {
                        isQuoted = true;
                        continue;
                    } else {
                        ++quoteCount;
                        if (quoteCount % 2 != 0) {
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

        table.append(FormatInHtml.tableClose());
        FileReader.writeFile(table, args[1]);

        if (quoteCountAll % 2 != 0) {
            System.out.println("Возможно Вы ввели лишнюю кавычку");
        }
    }
}

