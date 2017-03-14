package ru.academits.kalichkin.singleList.main;


import ru.academits.kalichkin.singleList.singleLinkedList.SinglyLinkedList;


public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();


        list.addFirst(1);
        list.addBefore(list.getNodeByIndex(0), 2);
        list.addBefore(list.getNodeByIndex(1), 3);
        list.addBefore(list.getNodeByIndex(2), 4);

        //list.removeBefore(list.getNodeByIndex(1));
        //list.setValue(0,5);

       //list.removeData(1);


       //list.addValue(0, 5);

        //list.removeNodeByValue("Андрей");
        list.reverse();
        list.print();


    }
}
