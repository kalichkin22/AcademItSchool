package ru.academits.kalichkin.main;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import ru.academits.kalichkin.hashTable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>(1, 5, 65, 3, 6, 35, 10);

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 65, 35));


        //System.out.println(table.contains(10));
        //System.out.println(table.size());
        //System.out.println(table.removeAll(list));
        //System.out.println(table.remove(3));
        //System.out.println(table.retainAll(list));
        //System.out.println(table.containsAll(list));
        //System.out.println(table.addAll(list));
        //table.clear();
        System.out.println(table);


    }
}