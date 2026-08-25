package day0821;

class ArrayStack<T> {
    private Object[] elements;
    private int top = -1;
    private int capacity;

    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
    }

    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack 已滿，無法 Push: " + item);
            return;
        }
        elements[++top] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack 為空，無法 Pop");
            return null;
        }
        T item = (T) elements[top];
        elements[top--] = null;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack 為空，無法 Peek");
            return null;
        }
        return (T) elements[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }
}

public class GenericArrayStackDemo {
    public static void main(String[] args) {
        System.out.println("--- 測試 ArrayStack<String> ---");
        ArrayStack<String> stringStack = new ArrayStack<>(2);
        stringStack.push("Hello");
        stringStack.push("World");
        stringStack.push("Overflow"); 

        System.out.println("頂端元素: " + stringStack.peek());
        System.out.println("Pop: " + stringStack.pop());
        System.out.println("Size: " + stringStack.size());

        System.out.println("\n--- 測試 ArrayStack<Integer> ---");
        ArrayStack<Integer> intStack = new ArrayStack<>(3);
        intStack.push(100);
        intStack.push(200);
        System.out.println("Pop: " + intStack.pop());
        System.out.println("isEmpty: " + intStack.isEmpty());
    }
}
