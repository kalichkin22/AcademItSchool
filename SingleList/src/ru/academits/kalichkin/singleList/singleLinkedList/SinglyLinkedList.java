package ru.academits.kalichkin.singleList.singleLinkedList;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int size;

    @SafeVarargs
    public SinglyLinkedList(T... values) {
        for (T e : values) {
            addFirst(e);
        }
        reverse();
    }

    public int getSize() {
        return size;
    }

    public Node<T> getFirstNode() {
        if (head == null) {
            throw new NoSuchElementException("Нет такого узла");
        }
        return head;
    }

    private void addFirst(T data) {
        Node<T> node = head;
        head = new Node<>(data, node);
        ++size;
    }


    public Node<T> getNodeByIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }


    public T getValue(int index) {
        return getNodeByIndex(index).getData();
    }


    public T setValue(int index, T data) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        Node<T> oldNode = new Node<T>(node.getData());

        node.setData(data);
        return oldNode.getData();
    }


    public void removeData(int index) {
        Node<T> node = getNodeByIndex(index);
        if (head == node) {
            head = node.getNext();
            --size;
            return;
        }
        for (Node<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p == node) {
                prev.setNext(p.getNext());
                --size;
            }
        }

    }


    public void addValue(int index, T data) {
        Node<T> node = getNodeByIndex(index);
        if (head == node) {
            addFirst(data);
        }
        for (Node<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p == node) {
                addAfter(prev, data);
            }
        }
    }


    public void addAfter(Node<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("Нет такого узла");
        }
        Node<T> newNode = new Node<>(data, node.getNext());
        node.setNext(newNode);
        ++size;
    }


    public void removeAfter(Node<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Нет такого узла");
        }
        node.setNext(node.getNext().getNext());
        --size;
    }


    public void removeNodeByValue(T data) {
        if (head.getData() == data) {
            head = head.getNext();
            --size;
            return;
        }
        for (Node<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                prev.setNext(p.getNext());
                --size;
            }
        }
    }

    public void reverse() {
        Node<T> node = head;
        Node<T> prev = null;

        while (node != null) {
            Node<T> temp = node.getNext();
            node.setNext(prev);

            head = node;
            prev = node;
            node = temp;
        }
    }


    public void print() {
        for (Node p = head; p != null; p = p.getNext()) {
            System.out.println(p.getData());
        }
    }

    public String toString() {
        StringBuilder br = new StringBuilder("[");
        for (Node p = head; p != null; p = p.getNext()) {
            String value = ", " + p.getData().toString();
            br.append(value);
        }
        return br.toString().replaceFirst(", ", "").trim() + "]";
    }
}



