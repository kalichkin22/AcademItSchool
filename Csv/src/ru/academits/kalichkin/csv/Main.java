package ru.academits.kalichkin.csv;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        List<Character> chars = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("csv.txt")));
             PrintWriter writer = new PrintWriter("html.txt")) {

            StringBuilder sb = new StringBuilder();
            sb.append("<table>").append(System.lineSeparator());
            int c;
            while ((c = reader.read()) != -1) {
                chars.add((char) c);
            }
            String a = "";
            String b = "";
            String g = "";

            for (Character e : chars) {
                if (chars.contains(',')) {
                    a += e;
                }
            }
            sb.append(a);


            System.out.println(sb);

            for (Character e : chars) {
                writer.print(e);
            }
        }
    }
}
