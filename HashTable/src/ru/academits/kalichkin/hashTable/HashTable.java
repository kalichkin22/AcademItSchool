package ru.academits.kalichkin.hashTable;

import java.util.*;


public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] table;
    private int size;
    private int modCount;

    @SuppressWarnings("unchecked")
    public HashTable(T... elements) {
        this.table = new ArrayList[16];
        this.addAll(Arrays.asList(elements));
    }

    private int getIndex(T element) {
        return Math.abs((element != null ? element.hashCode() : 0) % table.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T element) {
        int index = getIndex(element);
        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }
        table[index].add(element);
        ++size;
        ++modCount;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        int index = getIndex((T) o);
        return table[index] != null && table[index].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(table, size());
    }

    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        for (ArrayList<T> e : table) {
            if (e != null) {
                for (int i = 0; i < e.size(); ++i) {
                    a[i] = (E) e.get(i);
                }
            }
        }
        return a;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        int index = getIndex((T) o);
        if (table[index] == null) {
            return false;
        }
        if (table[index].contains(o)) {
            table[index].remove(o);
            ++modCount;
            --size;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (ArrayList<T> e : table) {
            if (e != null) {
                if (c.containsAll(e)) {
                    e.removeAll(c);
                    modified = true;
                }
            }
        }
        return modified;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (ArrayList<T> e : table) {
            if (e != null) {
                if (!c.containsAll(e)) {
                    e.retainAll(c);
                    modified = true;
                }
            }
        }
        return modified;
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
    public boolean addAll(Collection<? extends T> c) {
        for (T e : c) {
            this.add(e);
        }
        if (c.size() != 0) {
            ++modCount;
        }
        return true;
    }

    @Override
    public void clear() {
        if (size != 0) {
            ++modCount;
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
        }
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
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
            ArrayList<T>[] old = table;
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
}
