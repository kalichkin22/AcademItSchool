package ru.academits.kalichkin.main;

import ru.academits.kalichkin.hashTable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>(0, 20, 3, 4, 56, 6, null);

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(null, 20, 3, 15));

        //System.out.println(table.contains(7));
        //System.out.println(table.removeAll(list));
        //System.out.println(table.remove(56));
        //System.out.println(table.retainAll(list));
        //System.out.println(table.containsAll(list));
        //System.out.println(table.addAll(list));
        //table.clear();
        //System.out.println(table.size());
        //System.out.println(table);


        for (Object e : table) {
            System.out.println(e);
        }

    }
}
