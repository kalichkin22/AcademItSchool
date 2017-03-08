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
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            s = sb.toString();



            System.out.println(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}