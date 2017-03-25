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
        size++;
        modCount++;
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

    private List<T> toList() {
        ArrayList<T> newList = new ArrayList<>();
        for (ArrayList<T> e : table) {
            if (e != null) {
                newList.addAll(e);
            }
        }
        return newList;
    }

    @Override
    public Object[] toArray() {
        return toList().toArray();
    }

    @Override
    @SuppressWarnings({"unchecked", "SuspiciousSystemArraycopy"})
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) Arrays.copyOf(this.toArray(), size, a.getClass());
        }
        System.arraycopy(this.toArray(), 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
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

        if (table[index].remove(o)) {
            modCount++;
            size--;
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        int newSize = 0;
        for (ArrayList<T> e : table) {
            if (e != null) {
                e.removeAll(c);
                newSize += e.size();
            }
        }
        if (size != newSize) {
            size = newSize;
            modified = true;
        }

        modCount = modCount + (size - newSize);
        return modified;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        int newSize = 0;
        for (ArrayList<T> e : table) {
            if (e != null) {
                e.retainAll(c);
                newSize += e.size();
            }
        }
        if (size != newSize) {
            size = newSize;
            modified = true;
        }
        modCount = modCount + (size - newSize);
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
            modCount++;
        }
        return c.size() != 0;
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
        int indexArray;
        int indexList;
        int count;
        int estimatedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            checkForModification();

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int i = indexArray;
            int j = indexList;

            while (table[i] == null || table[i].size() == 0) {
                i++;
            }

            if (j < table[i].size() - 1) {
                indexList = j + 1;
            } else {
                indexArray = i + 1;
                indexList = 0;
            }

            count++;
            return table[i].get(j);
        }


        final void checkForModification() {
            if (modCount != estimatedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
