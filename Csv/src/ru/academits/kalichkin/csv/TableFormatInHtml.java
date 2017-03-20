package ru.academits.kalichkin.csv;

class TableFormatInHtml {
    private static final String HTML = "<html>";
    private static final String HTML_CLOSE = "</html>";
    private static final String HEAD = "<head>";
    private static final String HEAD_CLOSE = "</head>";
    private static final String META = "<meta charset=\"utf-8\">";
    private static final String TITLE = "<title> Разбор формата CSV </title>";
    private static final String BODY = "<body>";
    private static final String BODY_CLOSE = "</body>";
    private static final String TABLE = "<table bordercolor=\"black\" border=\"1\" width=\"80%\">";
    private static final String TABLE_CLOSE = "</table>";
    private static final String ROW = "<tr><td>";
    private static final String DETAIL = "</td><td>";
    private static final String BREAK = "<br/>";

    static String getROW() {
        return ROW;
    }

    static String getDETAIL() {
        return DETAIL;
    }

    static String getBREAK() {
        return BREAK;
    }

    static StringBuilder openTable() {
        StringBuilder table = new StringBuilder();
        return table.append(HTML).append(HEAD).append(META).append(TITLE).append(HEAD_CLOSE).append(BODY).append(TABLE).append(System.lineSeparator());
    }

    static StringBuilder closeTable() {
        StringBuilder table = new StringBuilder();
        return table.append(TABLE_CLOSE).append(BODY_CLOSE).append(HTML_CLOSE);
    }
}
