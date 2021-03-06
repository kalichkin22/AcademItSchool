package ru.academits.kalichkin.arraylist.arraylist;

import java.util.*;

public class ArrayList<T> implements List<T> {

    private Object[] items;
    private int size;
    private int modCount;

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
            items = Arrays.copyOf(items, size);
        }
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
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
        T item = getItem(index);
        this.items[index] = element;
        return item;

    }

    private void increaseCapacity() {
        if (items.length == 0) {
            items = new Object[10];
        }
        Object[] old = items;
        items = new Object[old.length * 2];
        System.arraycopy(old, 0, items, 0, old.length);
    }

    @Override
    public boolean add(T element) {
        modCount++;
        if (items.length <= size) {
            increaseCapacity();
        }
        items[size++] = element;
        return true;
    }


    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        modCount++;
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
        if (c.size() != 0) {
            modCount++;
        }
        return c.size() != 0;
    }


    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Object[] array = c.toArray();
        if (items.length < size + array.length) {
            items = Arrays.copyOf(items, items.length + array.length);
        }
        if (size - index > 0) {
            System.arraycopy(items, index, items, index + array.length, size - index);
        }

        System.arraycopy(array, 0, items, index, array.length);
        size += array.length;

        if (array.length != 0) {
            modCount++;
        }
        return array.length != 0;
    }

    @Override
    public T remove(int index) {
        Object[] old = items;
        T item = getItem(index);
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        if (index <= size - 1) {
            System.arraycopy(old, index + 1, items, index, old.length - index - 1);
        }
        size--;
        modCount++;

        return item;
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
                modCount++;
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
                modified = true;
            }
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
                modified = true;
            }
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
        if (size != 0) {
            modCount++;
            for (int i = 0; i < size; i++) {
                items[i] = null;
            }
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
        int estimatedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            checkForModification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] old = items;
            if (i >= old.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            lastRet = i;
            return (T) old[lastRet];
        }

        final void checkForModification() {
            if (modCount != estimatedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T previous() {
            checkForModification();
            int i = cursor - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            Object[] old = ArrayList.this.items;
            if (i >= old.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i;
            lastRet = i;
            return (T) old[lastRet];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForModification();

            try {
                ArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                estimatedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }


        @Override
        public void set(T t) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForModification();
            try {
                ArrayList.this.set(lastRet, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(T t) {
            checkForModification();
            try {
                int i = cursor;
                ArrayList.this.add(i, t);
                cursor = i + 1;
                lastRet = -1;
                estimatedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
