package ru.academits.kalichkin.main;


import ru.academits.kalichkin.hashTable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>(1, 5, 65, 3, 6, 35, null, null, 10);

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(null, 6, 35));


        //System.out.println(table.contains(7));
        System.out.println(table.removeAll(list));
        //System.out.println(table.remove());
        //System.out.println(table.retainAll(list));
        //System.out.println(table.containsAll(list));
        //System.out.println(table.addAll(list));
        //table.clear();
        //System.out.println(table.size());

        System.out.println(table);


    }
}
