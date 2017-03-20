package ru.academits.kalichkin.csv;

import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    private static final String HTML = "<html>";
    private static final String HTML_CLOSE = "</html>";
    private static final String HEAD = "<head>";
    private static final String HEAD_CLOSE = "</head>";
    private static final String META = "<meta charset=\"utf-8\">";
    private static final String TITLE = "<title> Разбор формата CSV </title>";
    private static final String BODY = "<body>";
    private static final String BODY_CLOSE = "</body>";
    private static final String TABLE = "<table bordercolor=\"black\" border=\"1\" width=\"\">";
    private static final String TABLE_CLOSE = "</table>";
    private static final String ROW = "<tr><td>";
    private static final String DETAIL = "</td><td>";
    private static final String BREAK = "<br/>";
    private static final char SEPARATOR = ',';
    private static final char QUOTE = '"';


    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }

            List<String> list = FileReader.readFile(args[0]);
            StringBuilder table = new StringBuilder();
            table.append(HTML).append(HEAD).append(META).append(TITLE).append(HEAD_CLOSE).append(BODY).append(TABLE).append(System.lineSeparator());

            boolean isQuoted = false;
            int quoteCount = 0;

            for (String e : list) {
                if (!isQuoted) {
                    table.append(ROW);
                }

                for (int i = 0; i < e.length(); ++i) {
                    char c = e.charAt(i);
                    if (c == QUOTE) {
                        ++quoteCount;
                        if (!isQuoted) {
                            isQuoted = true;
                        } else {
                            isQuoted = false;
                            if (i == e.length() - 1) {
                                table.append(BREAK).append(System.lineSeparator());
                            }
                        }
                        if (quoteCount % 2 != 0 && quoteCount > 2) {
                            table.append(c);
                        }
                        continue;
                    }

                    if (!isQuoted) {
                        if (c == SEPARATOR) {
                            quoteCount = 0;
                            if (i == e.length() - 1) {
                                table.append(DETAIL);
                                table.append(BREAK).append(System.lineSeparator());
                                continue;
                            }
                            table.append(DETAIL);
                            continue;
                        }
                    }

                    if (c == '<') {
                        table.append("&lt;");
                        continue;
                    }
                    if (c == '>') {
                        table.append("&gt;");
                        continue;
                    }
                    if (c == '&') {
                        table.append("&amp;");
                        continue;
                    }

                    table.append(c);

                    if (!isQuoted && i == e.length() - 1) {
                        table.append(BREAK).append(System.lineSeparator());
                    }
                }
            }

            table.append(TABLE_CLOSE).append(BODY_CLOSE).append(HTML_CLOSE);
            FileReader.writeFile(table, args[1]);

            if (quoteCount % 2 != 0) {
                throw new NoSuchElementException();
            }

        } catch (NoSuchElementException e) {
            System.out.println("Возможно Вы ввели лишнюю кавычку" + System.lineSeparator() +
                    "* Если ячейка таблицы содержит запятую или перевод строки, " + System.lineSeparator() +
                    "* то в таком случае содержимое ячейки заключается в двойные кавычки." + System.lineSeparator() +
                    "* Если же ячейка содержит двойную кавычку, то эта кавычка дублируется.");
        } catch (IllegalArgumentException e) {
            System.out.println("Необходимо ввести два аргумента! " +
                    "* Первый аргумент имя исходного файла, второй аргумент - имя нового файла");
        }
    }
}


