package ru.academits.kalichkin.main;

import ru.academits.kalichkin.hashTable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>(0, 20, 3, 4, 56, 6, null,15);

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(null, 1, 65, 35));

        //System.out.println(table.contains(7));
        //System.out.println(table.removeAll(list));
        //System.out.println(table.remove(5));
        //System.out.println(table.retainAll(list));
        //System.out.println(table.containsAll(list));
        //System.out.println(table.addAll(list));
        //table.clear();
        //System.out.println(table.size());
        //System.out.println(table);

        for (Integer e : table) {
            System.out.println(e);
        }

    }
}
