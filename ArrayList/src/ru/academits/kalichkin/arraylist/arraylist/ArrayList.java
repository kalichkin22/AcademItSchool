package ru.academits.kalichkin.arraylist.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {

    private Object[] items = new Object[10];
    private int size;

    public ArrayList(int capacity) {
        if (capacity > 0) {
            this.items = new Object[capacity];
        } else if (capacity == 0) {
            this.items = new Object[]{};
        } else {
            throw new IllegalArgumentException("Недопустимая вместимость: " +
                    capacity);
        }
    }

    public ArrayList() {
        this.items = new Object[]{};
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T get(int index) {
        return (T) items[index];

    }

    @Override
    public T set(int index, T element) {
        return (T) (items[index] = element);
    }

    @Override
    public boolean add(T element) {
        if (items.length <= size) {
            Object[] old = items;
            items = new Object[old.length + 1];
            System.arraycopy(old, 0, items, 0, old.length);
        }
        items[size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        } else {
            Object[] old = items;
            items = Arrays.copyOf(old, size + 1);
            System.arraycopy(items, index, items, index + 1, size - index);

            items[index] = element;
            size++;
        }
    }

    @Override
    public T remove(int index) {
        Object[] old = items;
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        } else {
            if (index <= size - 1) {
                items = Arrays.copyOf(old, size - 1);
                System.arraycopy(old, index + 1, items, index, size - index - 1);
            }
            size--;
        }
        return (T) old[index];
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }


    public String toString() {
        return Arrays.toString(items);
    }


    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (items[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(items[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
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
    public int lastIndexOf(Object o) {
        return 0;
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
