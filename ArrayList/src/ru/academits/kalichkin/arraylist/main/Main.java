package ru.academits.kalichkin.arraylist.main;

import ru.academits.kalichkin.arraylist.arraylist.ArrayList;

//import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(8);
        a.add(5);
        a.add(6);
        a.add(4);
        a.add(4);
        a.add(4);
        a.add(5);

        a.add(3,6);


        System.out.println(a);

        ArrayList<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(3);
        b.add(4);
        b.add(2);
        b.add(5);

        System.out.println(b);

        //System.out.println(a.containsAll(b));

        System.out.println(a.removeAll(b));
        //System.out.println(a.retainAll(b));

        //a.ensureCapacity(15);

        //a.addAll(b);
        //a.addAll(b);
        System.out.println(a);

    }
}
