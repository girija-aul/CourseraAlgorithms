import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first = null;
    private Node last = null;

    private class Node {
        Item data;
        Node next;
        Node prev;

        Node(Item item, Node next, Node prev) {
            this.data = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        if (!this.validateAndCheckInsertionOfFirstElement(item)) {
            this.first = new Node(item, this.first, null);
            this.first.next.prev = this.first;
            ++this.size;
        }

    }

    public void addLast(Item item) {
        if (!this.validateAndCheckInsertionOfFirstElement(item)) {
            this.last = new Node(item, null, this.last);
            this.last.prev.next = this.last;
            ++this.size;
        }
    }

    public Item removeFirst() {
        Item item = this.validateAndCheckRemovalOfLastElement();
        if (item == null) {
            item = this.first.data;
            this.first = this.first.next;
            this.first.prev = null;
            --this.size;
        }
        return item;
    }

    public Item removeLast() {
        Item item = this.validateAndCheckRemovalOfLastElement();
        if (item == null) {
            item = this.last.data;
            this.last = this.last.prev;
            this.last.next = null;
            --this.size;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private boolean validateAndCheckInsertionOfFirstElement(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (this.size == 0) {
            this.first = new Node(item, null, null);
            this.last = this.first;
            ++this.size;
            return true;
        }
        return false;
    }

    private Item validateAndCheckRemovalOfLastElement() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException();
        } else if (this.size == 1) {
            Item item = this.first.data;
            this.first = null;
            this.last = null;
            this.size = 0;
            return item;
        }
        return null;
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.data;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Deque<String> strDeque = new Deque<String>();
        strDeque.addFirst("Aman");
        strDeque.addLast("Girja");
        strDeque.addLast("Ishaan");

        for (String name : strDeque) {
            StdOut.println(name);
        }

        StdOut.println(strDeque.removeLast());
        StdOut.println(strDeque.removeLast());
        StdOut.println(strDeque.removeLast());

        Deque<Integer> deque = new Deque<Integer>();
        StdOut.println(deque.isEmpty());
        deque.addLast(1);
        StdOut.println(deque.removeFirst());
        deque.addLast(3);
        StdOut.println(deque.removeLast());
        StdOut.println(deque.size());
        deque.addFirst(6);
        StdOut.println(deque.removeLast());
        /*
         * Deque<Integer> deque = new Deque<Integer>() deque.addLast(0)
         * deque.removeFirst() ==> 0 deque.isEmpty() ==> true deque.addFirst(3)
         * deque.removeLast() ==> 0
         */
    }
}
