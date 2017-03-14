package ru.academits.kalichkin.singleList.main;


import ru.academits.kalichkin.singleList.singleLinkedList.SinglyLinkedList;


public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(1, 2, 3, 4, 5, 6, 7, 8);


        //list.removeBefore(list.getNodeByIndex(1));
        //list.setValue(0,5);

        //list.removeData(0);


        //list.addValue(0, 5);

        //list.removeNodeByValue(2);
        list.reverse();
        list.print();



    }
}
