package ru.academits.kalichkin.main;

import ru.academits.kalichkin.linkedlist.LinkedList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        LinkedList<Integer> list = new LinkedList<>(1, 2, 3, null, 4);
        java.util.LinkedList<Integer> list2 = new java.util.LinkedList<>(Arrays.asList(1, 2, 3, null, 4));

        //list.addFirst(null);
        //list.addLast(500);
        //System.out.println(list2.getFirst());
        //System.out.println(list.getLast());
        //System.out.println(list.offerFirst(100));
        //System.out.println(list.offerLast(200));
        //System.out.println(list.removeFirst());
        //System.out.println(list.removeLast());
        //System.out.println(list.pollFirst());
        //System.out.println(list.pollLast());
        //System.out.println(list.peekFirst());
        //System.out.println(list.peekLast());
        //System.out.println(list.size());
        //System.out.println(list.peekLast());
        //System.out.println(list.remove(new Integer(2)));
        //System.out.println(list.removeLastOccurrence(null));
        //list.add(3, 5);
        //System.out.println(list.lastIndexOf(null));
        //System.out.println(list.containsAll(list2));
        //System.out.println(list.removeAll(list2));
        //System.out.println(list.retainAll(list2));
        System.out.println(list.addAll(5, list2));
        //list2.clear();
        //System.out.println(list.get(2));

        //System.out.println(list.size());
        System.out.println(list);

    }
}
