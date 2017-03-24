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

    public LinkedList(Collection<? extends T> c) {
        addAll(c);
    }

    @Override
    public int size() {
        return size;
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
            throw new NoSuchElementException("Нет такого элемента");
        }
        return head.getData();
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("Нет такого элемента");
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
        size--;
        modCount++;
        return firstValue;
    }

    @Override
    public T removeLast() {
        T lastValue = tail.getData();
        tail = tail.getPrev();
        tail.setNext(null);
        size--;
        modCount++;
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
    public boolean offer(T data) {
        return offerLast(data);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T poll() {
        return removeFirst();
    }

    @Override
    public T element() {
        return getFirst();
    }

    @Override
    public T peek() {
        return getFirst();
    }

    @Override
    public void push(T data) {
        addFirst(data);
    }

    @Override
    public T pop() {
        return removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T removeNode(Node<T> node) {
        T data = node.getData();
        Node<T> next = node.getNext();
        Node<T> prev = node.getPrev();

        if (prev == null) {
            head = next;
        } else {
            prev.setNext(next);
            node.setPrev(null);
        }

        if (next == null) {
            tail = prev;
        } else {
            next.setPrev(prev);
            node.setNext(null);
        }

        node.setData(null);
        size--;
        modCount++;
        return data;
    }


    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        for (Node<T> p = tail; p != null; p = p.getPrev()) {
            if (Objects.equals(p.getData(), o)) {
                removeNode(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        return removeNode(getNodeByIndex(index));
    }

    @Override
    public boolean remove(Object o) {
        if (Objects.equals(head.getData(), o)) {
            removeFirst();
            return true;
        } else if (Objects.equals(tail.getData(), o)) {
            removeLast();
            return true;
        } else {
            for (Node<T> p = head; p != null; p = p.getNext()) {
                if (Objects.equals(p.getData(), o)) {
                    removeNode(p);
                    return true;
                }
            }
        }

        return false;
    }

    private void addBefore(T data, Node<T> node) {
        Node<T> prev = node.getPrev();
        Node<T> newNode = new Node<>(data, prev, node);

        node.setPrev(newNode);
        if (prev == null) {
            head = newNode;
        } else {
            prev.setNext(newNode);
        }

        size++;
        modCount++;
    }

    @Override
    public boolean add(T data) {
        return offer(data);
    }

    private Node<T> getNodeByIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    @Override
    public void add(int index, T data) {
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            addBefore(data, getNodeByIndex(index));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends T> c) {
        for (Object e : c) {
            this.addLast((T) e);
        }
        if (c.size() != 0) {
            modCount++;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        Object[] array = c.toArray();
        for (int i = array.length - 1; i >= 0; i--) {
            add(index, (T) array[i]);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (int i = 0; i < size; ++i) {
            if (c.contains(this.get(i))) {
                this.remove(this.get(i));
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
            if (!c.contains(this.get(i))) {
                this.remove(this.get(i));
                --i;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node<T> p = head; p != null; p = p.getNext()) {
            if (Objects.equals(p.getData(), o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        for (Node<T> p = tail; p != null; p = p.getPrev()) {
            if (Objects.equals(p.getData(), o)) {
                return index;
            }
            index--;
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
        Object[] array = new Object[size];
        int i = 0;
        for (Node<T> p = head; p != null; p = p.getNext()) {
            array[i++] = p.getData();
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            a = (E[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        }

        int i = 0;
        for (Node<T> p = head; p != null; p = p.getNext()) {
            ((Object[]) a)[i++] = p.getData();
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).getData();
    }

    @Override
    public T set(int index, T data) {
        Node<T> node = getNodeByIndex(index);
        T oldValue = node.getData();
        node.setData(data);
        return oldValue;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListItr(index);
    }


    private class ListItr implements ListIterator<T> {
        private Node<T> lastReturned;
        private Node<T> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            next = (index == size) ? null : getNodeByIndex(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            checkForModification();
            if (!hasNext()) {
                throw new NoSuchElementException("Нет такого элемента");
            }
            lastReturned = next;
            next = next.getNext();
            nextIndex++;
            return lastReturned.getData();
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public T previous() {
            checkForModification();
            if (!hasPrevious()) {
                throw new NoSuchElementException("Нет такого элемента");
            }
            lastReturned = next = (next == null) ? tail : next.getPrev();
            nextIndex--;
            return lastReturned.getData();
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForModification();
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node<T> lastNext = lastReturned.getNext();
            removeNode(lastReturned);

            if (next == lastReturned) {
                next = lastNext;
            } else {
                nextIndex--;
            }

            lastReturned = null;
            expectedModCount++;
        }

        public void set(T e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }

            checkForModification();
            lastReturned.setData(e);
        }

        public void add(T e) {
            checkForModification();
            lastReturned = null;
            if (next == null) {
                addLast(e);
            } else {
                addBefore(e, next);
            }

            nextIndex++;
            expectedModCount++;
        }

        final void checkForModification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    private class DescendingIterator implements Iterator<T> {
        private final ListItr itr = new ListItr(size());

        public boolean hasNext() {
            return itr.hasPrevious();
        }

        public T next() {
            return itr.previous();
        }

        public void remove() {
            itr.remove();
        }
    }


    @Override
    public Iterator<T> descendingIterator() {
        return new DescendingIterator();
    }


    public String toString() {
        StringBuilder br = new StringBuilder("[");
        for (Node p = head; p != null; p = p.getNext()) {
            br.append(p.getData());
            if (p.getNext() != null) {
                br.append(", ");
            }
        }
        br.append("]");
        return br.toString();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
