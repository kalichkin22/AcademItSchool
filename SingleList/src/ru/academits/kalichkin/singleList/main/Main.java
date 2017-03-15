package ru.academits.kalichkin.singleList.main;


import ru.academits.kalichkin.singleList.singleLinkedList.SinglyLinkedList;


public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


        //System.out.println(list.removeAfter(list.getNodeByIndex(1)));
        //System.out.println(list.setValue(6,5));

        System.out.println(list.removeData(7));

        //list.addValue(0, 5);

        //System.out.println(list.removeNodeByValue(8));
        //list.reverse();

        //System.out.println(list.setValue(1, 100));
        System.out.println(list);

    }
}
