package ru.academits.kalichkin.singleList.singleLinkedList;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private int size;

    @SafeVarargs
    public SinglyLinkedList(T... values) {
        for (int i = values.length - 1; i >= 0; i--) {
            T e = values[i];
            addFirst(e);
        }
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
        Node<T> node = getNodeByIndex(index);
        T oldValue = node.getData();
        node.setData(data);
        return oldValue;
    }


    public T removeData(int index) {
        if (index != 0) {
            return removeAfter(getNodeByIndex(index - 1));
        } else {
            T oldValue = head.getData();
            head = head.getNext();
            --size;
            return oldValue;
        }
    }


    public void addValue(int index, T data) {
        if (index != 0) {
            addAfter(getNodeByIndex(index - 1), data);
            return;
        }
        addFirst(data);
    }


    public void addAfter(Node<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("Нет такого узла");
        }
        Node<T> newNode = new Node<>(data, node.getNext());
        node.setNext(newNode);
        ++size;
    }


    public T removeAfter(Node<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Нет такого узла");
        }
        Node<T> oldNode = node.getNext();
        node.setNext(node.getNext().getNext());
        --size;
        return oldNode.getData();
    }


    public T removeNodeByValue(T data) {
        if (head.getData() == data) {
            head = head.getNext();
            --size;
            return data;
        }
        for (Node<T> p = head, prev = null; p != null; prev = p, p = p.getNext()) {
            if (p.getData() == data) {
                prev.setNext(p.getNext());
                --size;
                return data;
            }
        }
        return data;
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
            br.append(p.getData().toString());
            if (p.getNext() != null) {
                br.append(", ");
            }
        }
        br.append("]");
        return br.toString();
    }
}



