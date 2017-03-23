package ru.academits.kalichkin.linkedlist;


import java.util.*;

public class LinkedList<T> implements List<T>, Deque<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    @SafeVarargs
    public LinkedList(T... values) {
        for (int i = values.length - 1; i >= 0; i--) {
            T e = values[i];
            addFirst(e);
        }
    }

    @Override
    public void addFirst(T data) {
        Node<T> node = head;
        Node<T> newNode = new Node<>(data, null, node);
        head = newNode;
        if (node == null) {
            tail = newNode;
        } else {
            node.setPrev(newNode);
        }
        size++;
        modCount++;
    }

    @Override
    public void addLast(T data) {
        Node<T> node = tail;
        tail = new Node<>(data, node, null);
        if (node == null) {
            head = tail;
        } else {
            node.setNext(tail);
        }
        size++;
        modCount++;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Нет такого узла");
        }
        return head.getData();
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("Нет такого узла");
        }
        return tail.getData();
    }

    @Override
    public boolean offerFirst(T data) {
        addFirst(data);
        return true;
    }

    @Override
    public boolean offerLast(T data) {
        addLast(data);
        return true;
    }

    @Override
    public T removeFirst() {
        T firstValue = head.getData();
        head = head.getNext();
        --size;
        return firstValue;
    }

    @Override
    public T removeLast() {
        T lastValue = tail.getData();
        tail = tail.getPrev();
        tail.setNext(null);
        --size;
        return lastValue;
    }

    @Override
    public T pollFirst() {
        return removeFirst();
    }

    @Override
    public T pollLast() {
        return removeLast();
    }

    @Override
    public T peekFirst() {
        return getFirst();
    }

    @Override
    public T peekLast() {
        return getLast();
    }


    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public T poll() {
        return null;
    }

    @Override
    public T element() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public void push(T t) {

    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public Iterator<T> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
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


    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
