package ru.academits.kalichkin.csv;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("csv.txt")));
             PrintWriter writer = new PrintWriter("html.txt")) {

            StringBuilder sb = new StringBuilder();
            String s;
            sb.append("<table>\n");
            while ((s = br.readLine()) != null) {
                s = s.replace(",","</td>");
                sb.append("<tr>").append(s).append("</tr>\n");
            }

            sb.append("</table>");
            s = sb.toString();

            System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}