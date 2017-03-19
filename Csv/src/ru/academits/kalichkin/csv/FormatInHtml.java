package ru.academits.kalichkin.csv;


class FormatInHtml {
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



    static StringBuilder tableOpen(String... strings) {
        StringBuilder table = new StringBuilder();
        return table.append(HTML).append(HEAD).append(META).append(TITLE).append(HEADCLOSE).append(BODY).append(TABLE).append(System.lineSeparator());
    }

    static StringBuilder tableClose(String... strings) {
        StringBuilder table = new StringBuilder();
        return table.append(TABLECLOSE).append(BODYCLOSE).append(HTMLCLOSE);
    }

}
