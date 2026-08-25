package day0821;

import java.util.Arrays;

class CircularQueue<T> {
    private Object[] elements;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean enqueue(T item) {
        if (size == capacity) {
            System.out.println("Queue 已滿，無法放入: " + item);
            return false;
        }
        elements[rear] = item;
        rear = (rear + 1) % capacity; 
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue 為空，無法取出");
            return null;
        }
        T item = (T) elements[front];
        elements[front] = null; 
        front = (front + 1) % capacity; 
        size--;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printStatus(String action) {
        System.out.printf("%-18s -> Array: %-24s | front: %d | rear: %d | size: %d\n",
                action, Arrays.toString(elements), front, rear, size);
    }
}

public class CircularQueuePractice {
    public static void main(String[] args) {
        CircularQueue<String> queue = new CircularQueue<>(4);

        System.out.println("=== 開始依序執行操作 ===");
        
        queue.enqueue("A");
        queue.printStatus("enqueue A");

        queue.enqueue("B");
        queue.printStatus("enqueue B");

        queue.enqueue("C");
        queue.printStatus("enqueue C");

        queue.dequeue();
        queue.printStatus("dequeue");

        queue.dequeue();
        queue.printStatus("dequeue");

        queue.enqueue("D");
        queue.printStatus("enqueue D");

        queue.enqueue("E");
        queue.printStatus("enqueue E");

        queue.enqueue("F");
        queue.printStatus("enqueue F");

        queue.dequeue();
        queue.printStatus("dequeue");

        queue.enqueue("G");
        queue.printStatus("enqueue G");

        System.out.println("\n=== 依 FIFO 順序取出所有剩餘元素 ===");
        while (!queue.isEmpty()) {
            System.out.println("取出: " + queue.dequeue());
        }
    }
}
