package ru.academits.kalichkin.csv;


import java.io.*;


public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("csv.txt")));
             PrintWriter writer = new PrintWriter("html.txt")) {

            StringBuilder sb = new StringBuilder();
            String s;
            sb.append("<table>\n");
            while ((s = br.readLine()) != null) {
                sb.append("<tr>");
                s = s.replace(",", "</td><td>");
                sb.append("<td>").append(s).append("</td></tr><br/>\n");
            }
            sb.append("</table>");
            s = sb.toString();

            writer.print(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}