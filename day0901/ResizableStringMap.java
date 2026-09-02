package day0901;

import java.util.LinkedList;

public class ResizableStringMap {
    private static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry>[] buckets;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @SuppressWarnings("unchecked")
    public ResizableStringMap() {
        this.buckets = new LinkedList[11];
        this.size = 0;
    }

    private int getBucketIndex(String key, int capacity) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(String key, String value) {
        if ((double) (size + 1) / buckets.length > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int index = getBucketIndex(key, buckets.length);
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets[index].add(new Entry(key, value));
        size++;
    }

    public String get(String key) {
        int index = getBucketIndex(key, buckets.length);
        if (buckets[index] != null) {
            for (Entry entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = buckets.length * 2 + 1;
        LinkedList<Entry>[] newBuckets = new LinkedList[newCapacity];
        for (LinkedList<Entry> bucket : buckets) {
            if (bucket != null) {
                for (Entry entry : bucket) {
                    int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                    if (newBuckets[newIndex] == null) {
                        newBuckets[newIndex] = new LinkedList<>();
                    }
                    newBuckets[newIndex].add(entry);
                }
            }
        }
        this.buckets = newBuckets;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return buckets.length;
    }
}
