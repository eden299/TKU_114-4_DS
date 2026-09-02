package day0901;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BookIsbnHashTable {
    private static class Entry {
        String isbn;
        String title;

        Entry(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    private LinkedList<Entry>[] buckets;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @SuppressWarnings("unchecked")
    public BookIsbnHashTable(int initialCapacity) {
        this.buckets = new LinkedList[initialCapacity > 0 ? initialCapacity : 11];
        this.size = 0;
    }

    private int getBucketIndex(String isbn) {
        return Math.abs(isbn.hashCode()) % buckets.length;
    }

    public void put(String isbn, String title) {
        if (getLoadFactor() > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int index = getBucketIndex(isbn);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        for (Entry entry : buckets[index]) {
            if (entry.isbn.equals(isbn)) {
                entry.title = title;
                return;
            }
        }
        buckets[index].add(new Entry(isbn, title));
        size++;
    }

    public String get(String isbn) {
        int index = getBucketIndex(isbn);
        if (buckets[index] != null) {
            for (Entry entry : buckets[index]) {
                if (entry.isbn.equals(isbn)) {
                    return entry.title;
                }
            }
        }
        return null;
    }

    public boolean remove(String isbn) {
        int index = getBucketIndex(isbn);
        if (buckets[index] != null) {
            for (Entry entry : buckets[index]) {
                if (entry.isbn.equals(isbn)) {
                    buckets[index].remove(entry);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public double getLoadFactor() {
        return (double) size / buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = buckets.length * 2 + 1;
        LinkedList<Entry>[] newBuckets = new LinkedList[newCapacity];
        for (LinkedList<Entry> bucket : buckets) {
            if (bucket != null) {
                for (Entry entry : bucket) {
                    int newIndex = Math.abs(entry.isbn.hashCode()) % newCapacity;
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new LinkedList<>();
                    }
                    newBuckets[newIndex].add(entry);
                }
            }
        }
        this.buckets = newBuckets;
    }

    public void printBucketReport() {
        System.out.println("Bucket Report (Capacity: " + buckets.length + ", Size: " + size + "):");
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("Bucket " + i + ": ");
            if (buckets[i] == null || buckets[i].isEmpty()) {
                System.out.println("Empty");
            } else {
                List<String> entries = new ArrayList<>();
                for (Entry entry : buckets[i]) {
                    entries.add("[" + entry.isbn + ": " + entry.title + "]");
                }
                System.out.println(String.join(" -> ", entries));
            }
        }
    }
}
