package ru.academits.kalichkin.arraylist.main;

import ru.academits.kalichkin.arraylist.arraylist.ArrayList;

//import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(2);
        a.add(9);
        a.add(9);
        a.add(9);
        a.add(9);
        a.add(9);
        a.add(9);


        System.out.println(a);

        java.util.ArrayList<Integer> b = new java.util.ArrayList<>();
        b.add(9);
        b.add(2);

        System.out.println(b);

        //System.out.println(a.containsAll(b));

        //System.out.println(a.removeAll(b));
        //System.out.println(a.retainAll(b));

        //a.ensureCapacity(8);

        //a.addAll(0,b);

        System.out.println(a);

    }
}
