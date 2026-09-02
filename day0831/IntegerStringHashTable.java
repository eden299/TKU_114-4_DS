package day0831;

import java.util.LinkedList;

public class IntegerStringHashTable {
    private static class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] buckets;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public IntegerStringHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];
        this.size = 0;
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(int key) {
        return Math.abs(key) % capacity;
    }

    public void put(int key, String value) {
        int idx = getBucketIndex(key);
        for (Entry entry : buckets[idx]) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }
        buckets[idx].add(new Entry(key, value));
        size++;
    }

    public String get(int key) {
        int idx = getBucketIndex(key);
        for (Entry entry : buckets[idx]) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean containsKey(int key) {
        return get(key) != null;
    }

    public boolean remove(int key) {
        int idx = getBucketIndex(key);
        for (Entry entry : buckets[idx]) {
            if (entry.key == key) {
                buckets[idx].remove(entry);
                size--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void bucketReport() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Bucket " + i + " (size " + buckets[i].size() + "): ");
            for (Entry e : buckets[i]) {
                System.out.print("[" + e.key + "->" + e.value + "] ");
            }
            System.out.println();
        }
    }
}
