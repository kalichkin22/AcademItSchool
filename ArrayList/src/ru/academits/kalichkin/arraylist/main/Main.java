package ru.academits.kalichkin.arraylist.main;

import ru.academits.kalichkin.arraylist.arraylist.ArrayList;

//import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> a = new ArrayList<>();

        System.out.println(a);




        ArrayList<Integer> b = new ArrayList<>();
        b.add(1);
        b.add(2);
        b.add(3);
        b.add(4);
        b.add(5);
        b.add(6);


        System.out.println(b);

        //a.trimToSize();
        //System.out.println(a.containsAll(b));
        //System.out.println(a);
        //System.out.println(a.removeAll(b));
        //System.out.println(a.retainAll(b));

        //a.ensureCapacity(28);

        //a.trimToSize();
        a.clear();
        System.out.println(a.size());
        System.out.println(a);


    }
}
