package ru.academits.kalichkin.hashTable;

import java.util.*;


public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] table;
    private int modCount;

    @SuppressWarnings("unchecked")
    public HashTable(T... elements) {
        this.table = new ArrayList[16];
        for (int i = 0; i < table.length; ++i) {
            this.table[i] = null;
        }
        Collections.addAll(this, elements);
        ++modCount;
    }

    @Override
    public int size() {
        int size = 0;
        for (ArrayList<T> e : table) {
            if (e != null) {
                for (T list : e) {
                    ++size;
                }
            }
        }
        return size;
    }

    @Override
    public boolean add(T element) {
        int index = Math.abs(element.hashCode() % table.length);
        for (int i = 0; i < table.length; ++i) {
            if (table[index] == null) {
                table[index] = new ArrayList<>();
            }
        }
        table[index].add(element);
        ++modCount;
        return true;
    }

    @Override
    public boolean isEmpty() {
        for (ArrayList<T> e : table) {
            if (e != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode() % table.length);
        for (ArrayList<T> e : table) {
            if (table[index] == null) {
                return false;
            }
        }
        for (int i = 0; i < table[index].size(); ++i) {
            if (table[index].get(i).equals(o)) {
                return true;
            }
        }
        return false;
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
                for (int j = 0; j < e.size(); ++j) {
                    a[j] = (E) e.get(j);
                }
            }
        }
        return a;
    }


    @Override
    public boolean remove(Object o) {
        int index = Math.abs(o.hashCode() % table.length);
        if (this.contains(o)) {
            table[index].remove(o);

            if (table[index].size() == 0) {
                table[index] = null;
            }
            ++modCount;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (ArrayList<T> e : table) {
            if (e != null) {
                for (int i = 0; i < e.size(); ++i) {
                    if (c.contains(e.get(i))) {
                        this.remove(e.get(i));
                        modified = true;
                    }
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
                for (int i = 0; i < e.size(); ++i) {
                    if (!c.contains(e.get(i))) {
                        this.remove(e.get(i));
                        modified = true;
                    }
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
        if (size() != 0) {
            ++modCount;
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
        }
    }


    public String toString() {
        return Arrays.toString(Arrays.copyOf(table, table.length));
    }


    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int estimatedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            checkForModification();
            int i = cursor;
            if (i >= size()) {
                throw new NoSuchElementException();
            }
            Object[] old = table;
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
