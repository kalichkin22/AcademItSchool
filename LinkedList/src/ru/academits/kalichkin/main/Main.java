package ru.academits.kalichkin.main;

import ru.academits.kalichkin.linkedlist.LinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, 4);
        //java.util.LinkedList<Integer> list2 = new java.util.LinkedList<>(Arrays.asList(1, 2, 3, 4));

        //list.addFirst(100);
        //list.addLast(500);

        //System.out.println(list.getFirst());
        //System.out.println(list.getLast());
        //System.out.println(list.offerFirst(100));
        //System.out.println(list.offerLast(200));
        //list.removeFirst();
        //list.removeLast();
        //System.out.println(list.pollFirst());
        //System.out.println(list.pollLast());
        //System.out.println(list.size());
        //System.out.println(list.peekLast());


        System.out.println(list);


    }
}
