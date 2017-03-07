package ru.academits.kalichkin.arraylist.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {

    private Object[] items;
    private int size;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Недопустимая вместимость: " +
                    capacity);
        } else {
            items = new Object[capacity];
        }
    }

    public ArrayList() {
        items = new Object[10];
    }

    public void trimToSize() {
        if (size < items.length) {
            items = (size == 0) ? new Object[]{} : Arrays.copyOf(items, size);
        }
    }

    @SuppressWarnings("unchecked")
    private T getValueItems(int index) {
        return (T) items[index];
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
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        } else {
            return getValueItems(index);
        }
    }

    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        } else {
            T valueItems = getValueItems(index);
            this.items[index] = element;
            return valueItems;
        }
    }

    @Override
    public boolean add(T element) {
        if (items.length <= size) {
            Object[] old = items;
            items = new Object[old.length * 2];
            System.arraycopy(old, 0, items, 0, old.length);
        }
        items[size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        } else {
            Object[] old = items;
            if (items.length <= size) {
                items = Arrays.copyOf(old, size + 1);
            } else {
                items = Arrays.copyOf(old, old.length);
            }
            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = element;
            size++;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();
        items = Arrays.copyOf(items, items.length + a.length);
        System.arraycopy(a, 0, items, size, a.length);
        size += a.length;
        return a.length != 0;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        } else {
            Object[] a = c.toArray();
            items = Arrays.copyOf(items, items.length + a.length);

            if (size - index > 0) {
                System.arraycopy(items, index, items, index + a.length, size - index);
            }

            System.arraycopy(a, 0, items, index, a.length);
            size += a.length;
            return a.length != 0;
        }
    }


    @Override
    public T remove(int index) {
        Object[] old = items;
        T valueItems = getValueItems(index);
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        } else {
            if (index <= size - 1) {
                System.arraycopy(old, index + 1, items, index, old.length - index - 1);
            }
            size--;
        }
        return valueItems;
    }

    @Override
    public boolean remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(items[index], o)) {
                if (index <= size - 1) {
                    Object[] old = items;
                    System.arraycopy(old, index + 1, items, index, old.length - index - 1);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, size));
        //return Arrays.toString(items);
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] old = c.toArray();
        for (Object e : old) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) Arrays.copyOf(items, size, a.getClass());
        }
        System.arraycopy(items, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] old = items;
        int i = 0;
        int j = 0;

        boolean modified = false;

        while (i < size) {
            if (!c.contains(old[i])) {
                old[j++] = old[i];
            }
            i++;
        }

        if (i != size) {
            System.arraycopy(old, i, old, j, size - i);
            j += size - i;
        }

        if (j != size) {
            for (int k = j; k < size; k++) {
                old[i] = null;
            }

            size = j;
            modified = true;
        }
        return modified;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] old = items;
        int i = 0;
        int j = 0;

        boolean modified = false;

        while (i < size) {
            if (c.contains(old[i])) {
                old[j++] = old[i];
            }
            i++;
        }

        if (i != size) {
            System.arraycopy(old, i, old, j, size - i);
            j += size - i;
        }
        if (j != size) {
            for (int k = j; k < size; k++) {
                old[i] = null;
            }

            size = j;
            modified = true;
        }

        return modified;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
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

    public void ensureCapacity(int minCapacity) {

    }
}
