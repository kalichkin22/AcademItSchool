package ru.academits.kalichkin.csv;

import java.util.List;
import java.util.NoSuchElementException;

public class Main {
    private static final char SEPARATOR = ',';
    private static final char QUOTE = '"';


    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }

            List<String> list = FileReader.readFile(args[0]);
            StringBuilder table = new StringBuilder();

            table.append(TableFormatInHtml.openTable());

            boolean isQuoted = false;
            int quoteCount = 0;

            for (String e : list) {
                if (!isQuoted) {
                    table.append(TableFormatInHtml.getROW());
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
                                table.append(TableFormatInHtml.getBREAK()).append(System.lineSeparator());
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
                                table.append(TableFormatInHtml.getDETAIL());
                                table.append(TableFormatInHtml.getBREAK()).append(System.lineSeparator());
                                continue;
                            }
                            table.append(TableFormatInHtml.getDETAIL());
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
                        table.append(TableFormatInHtml.getBREAK()).append(System.lineSeparator());
                    }
                    if (isQuoted && i == e.length() - 1) {
                        table.append(" ");
                    }

                }
            }

            table.append(TableFormatInHtml.closeTable());
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


