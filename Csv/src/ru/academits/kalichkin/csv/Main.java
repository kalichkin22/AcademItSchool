package ru.academits.kalichkin.csv;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("csv.txt")));
             PrintWriter writer = new PrintWriter("html.txt")) {

            StringBuilder sb = new StringBuilder();
            String cell = "";
            sb.append("<table>").append(System.lineSeparator());

            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }

            System.out.println(sb);
            writer.print(sb);
        }
    }
}
