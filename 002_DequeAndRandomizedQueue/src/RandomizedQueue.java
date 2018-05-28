import java.util.Arrays;
import java.util.Iterator;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        this.arr = (Item[]) new Object[1];
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
        // is the randomized queue empty?
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (this.size == this.arr.length) {
            this.resize(2 * this.size);
        }
        this.arr[this.size++] = item;
    }

    public Item dequeue() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int random = StdRandom.uniform(this.size);
        Item item = this.arr[random];
        this.arr[random] = this.arr[--this.size];
        this.arr[this.size] = null;
        if (this.size == this.arr.length/4) {
            this.resize(this.arr.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException();
        }
        int random = StdRandom.uniform(this.size);
        return this.arr[random];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] myList = Arrays.copyOf(arr, size);
        private int nCount = size;

        @Override
        public boolean hasNext() {
            return nCount > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int random = StdRandom.uniform(this.nCount);
            Item item = this.myList[random];
            this.myList[random] = this.myList[this.nCount - 1];
            this.myList[this.nCount - 1] = null;
            this.nCount--;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
    }

    private void resize(int newCapacity) {
        if (newCapacity < 1) {
            newCapacity = 1;
        }
        Item[] copy = (Item[]) new Object[newCapacity];
        for (int i = 0; i < this.size; ++i) {
            copy[i] = this.arr[i];
        }
        this.arr = copy;
    }

    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        q.enqueue("Aman");
        q.dequeue();
        q.enqueue("Girija");
        q.enqueue("Ishaan");
        
        q.enqueue("Aman1");
        q.enqueue("Girija1");
        q.enqueue("Ishaan1");
        
        q.enqueue("Aman2");
        q.enqueue("Girija2");
        q.enqueue("Ishaan2");

        Iterator<String> it1 = q.iterator();
        Iterator<String> it2 = q.iterator();
        
        while (it1.hasNext()) {
            StdOut.println(it1.next() + " = " + it2.next());
        }
    }

}
