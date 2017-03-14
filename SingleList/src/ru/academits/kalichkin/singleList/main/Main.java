package ru.academits.kalichkin.singleList.main;


import ru.academits.kalichkin.singleList.singleLinkedList.SinglyLinkedList;


public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();


        list.setFirst("Андрей");
        list.addBefore(list.getNodeByIndex(0), "Олег");
        list.addBefore(list.getNodeByIndex(1), "Кирилл");
        list.addBefore(list.getNodeByIndex(2), "Антон");

        //list.removeBefore(list.getNodeByIndex(1));
        //list.setValue(0,5);

       //list.removeData(1);


       //list.addValue(0, 5);

        list.removeNodeByValue("Андрей");
        System.out.println(list.getSize());
        list.print();


    }
}
