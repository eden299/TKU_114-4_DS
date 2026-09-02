package day0831;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayMinHeap {
    private int[] heap;
    private int size;

    public ArrayMinHeap() {
        this.heap = new int[10];
        this.size = 0;
    }

    public void add(int val) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        heap[size] = val;
        siftUp(size);
        size++;
    }

    public int peek() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        return heap[0];
    }

    public int remove() {
        if (isEmpty()) throw new NoSuchElementException("Heap is empty");
        int minVal = heap[0];
        heap[0] = heap[size - 1];
        size--;
        if (size > 0) {
            siftDown(0);
        }
        return minVal;
    }

    public int[] snapshot() {
        return Arrays.copyOf(heap, size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[index] < heap[parent]) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int smallest = index;

            if (left < size && heap[left] < heap[smallest]) smallest = left;
            if (right < size && heap[right] < heap[smallest]) smallest = right;

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        ArrayMinHeap minHeap = new ArrayMinHeap();
        int[] data = {45, 12, 85, 32, 89, 39, 69, 44, 42, 1, 68, 43, 22, 18, 97, 56, 38, 27, 10, 5};
        for (int val : data) {
            minHeap.add(val);
        }

        System.out.println("Snapshot after 20 insertions: " + Arrays.toString(minHeap.snapshot()));
        System.out.println("Peek min: " + minHeap.peek());
        
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.remove() + " ");
        }
        System.out.println();
    }
}
