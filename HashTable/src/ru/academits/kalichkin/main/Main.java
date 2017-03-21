package ru.academits.kalichkin.main;


import ru.academits.kalichkin.hashTable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        HashTable<Integer> table = new HashTable<>();

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 35, 65, 5, 6, 10));


        //System.out.println(table.contains(9));
        //System.out.println(table.removeAll(list));
        //System.out.println(table.remove(5));
        //System.out.println(table.retainAll(list));
        //System.out.println(table.containsAll(list));
        //System.out.println(table.addAll(list));
        table.clear();
        //System.out.println(table.size());

        System.out.println(table);


    }
}
