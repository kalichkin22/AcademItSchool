package ru.academits.kalichkin.arraylist.arraylist;

import java.util.*;
import java.util.function.Consumer;

public class ArrayList<T> implements List<T> {

    private Object[] items;
    private int size;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Недопустимая вместимость: " + capacity);
        }
        items = new Object[capacity];

    }


    public ArrayList() {
        items = new Object[10];
    }

    public void trimToSize() {
        if (size < items.length) {
            items = (size == 0) ? new Object[]{} : Arrays.copyOf(items, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < items.length) {
            items = Arrays.copyOf(items, items.length);
        } else {
            Object[] old = items;
            items = new Object[minCapacity];
            System.arraycopy(old, 0, items, 0, old.length);
        }
    }

    @SuppressWarnings("unchecked")
    private T getItem(int index) {
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
        }
        return getItem(index);
    }

    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        T valueItems = getItem(index);
        this.items[index] = element;
        return valueItems;

    }

    private void increaseCapacity() {
        Object[] old = items;
        items = new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    @Override
    public boolean add(T element) {
        if (items.length <= size) {
            increaseCapacity();
        }
        items[size++] = element;
        return true;
    }


    @Override
    public void add(int index, T element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        if (items.length <= size) {
            increaseCapacity();
        }
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;

    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        for (Object e : c) {
            this.add((T) e);
        }
        return c.size() != 0;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Object[] array = c.toArray();

        if (items.length < items.length + array.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }
        if (size - index > 0) {
            System.arraycopy(items, index, items, index + array.length, size - index);
        }

        System.arraycopy(array, 0, items, index, array.length);
        size += array.length;

        return array.length != 0;

    }


    @Override
    public T remove(int index) {
        Object[] old = items;
        T valueItems = getItem(index);
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        if (index <= size - 1) {
            System.arraycopy(old, index + 1, items, index, old.length - index - 1);
        }
        size--;

        return valueItems;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                if (i <= size - 1) {
                    Object[] old = items;
                    System.arraycopy(old, i + 1, items, i, old.length - i - 1);
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; ++i) {
            if (c.contains(items[i])) {
                this.remove(items[i]);
                --i;
            }
            modified = true;
        }
        return modified;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; ++i) {
            if (!c.contains(items[i])) {
                this.remove(items[i]);
                --i;
            }
            modified = true;
        }
        return modified;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOf(items, size));
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
        for (Object e : c) {
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
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
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
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        return new ListItr(index);
    }


    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] old = items;
            if (i >= old.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return (T) old[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }

            try {
                ru.academits.kalichkin.arraylist.arraylist.ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void forEachRemaining(Consumer<? super T> consumer) {
            Objects.requireNonNull(consumer);
            final int size = ru.academits.kalichkin.arraylist.arraylist.ArrayList.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            final Object[] elementData = ArrayList.this.items;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            while (i != size) {
                consumer.accept((T) elementData[i++]);
            }
            cursor = i;
            lastRet = i - 1;
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public T previous() {

            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            Object[] old = ArrayList.this.items;
            if (i >= old.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i;
            return (T) old[lastRet = i];
        }

        public void set(T e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }

            try {
                ArrayList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(T e) {

            try {
                int i = cursor;
                ArrayList.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
