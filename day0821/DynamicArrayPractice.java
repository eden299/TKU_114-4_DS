package day0821;

import java.util.Arrays;

class DynamicArray<T> {
    private Object[] data;
    private int size;
    private int capacity;

    public DynamicArray() {
        this(2); 
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            initialCapacity = 2;
        }
        this.capacity = initialCapacity;
        this.data = new Object[capacity];
        this.size = 0;
    }

    private void resize() {
        capacity *= 2;
        Object[] newData = new Object[capacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void add(T value) {
        add(size, value);
    }

    public void add(int index, T value) {
        checkIndexForAdd(index);
        if (size == capacity) {
            resize();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T value) {
        checkIndex(index);
        T oldValue = (T) data[index];
        data[index] = value;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) data[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }

        // 移除後最後一個無效格設為 null
        data[--size] = null;
        return removedValue;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public String toString() {
        Object[] currentElements = new Object[size];
        System.arraycopy(data, 0, currentElements, 0, size);
        return Arrays.toString(currentElements) + " (Capacity: " + capacity + ")";
    }
}

public class DynamicArrayPractice {
    public static void main(String[] args) {
        System.out.println("--- 測試 String 型態 ---");
        DynamicArray<String> strArr = new DynamicArray<>(2);
        strArr.add("A");
        strArr.add("B");
        System.out.println("新增 A, B 後: " + strArr);
        
        strArr.add("C"); // 觸發擴充 (2 -> 4)
        System.out.println("新增 C (觸發擴張): " + strArr);
        
        strArr.add(1, "INSERT");
        System.out.println("在 index 1 插入 INSERT: " + strArr);

        System.out.println("\n--- 測試 Integer 型態 ---");
        DynamicArray<Integer> intArr = new DynamicArray<>(2);
        intArr.add(10);
        intArr.add(20);
        intArr.add(30);
        System.out.println("目前內容: " + intArr);
        System.out.println("移除 index 1 (元素 " + intArr.remove(1) + "): " + intArr);

        System.out.println("\n--- 測試異常邊界 (index -1, size, 空結構刪除) ---");
        DynamicArray<String> emptyArr = new DynamicArray<>();

        try {
            emptyArr.remove(-1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲異常 (index -1 刪除): " + e.getMessage());
        }

        try {
            emptyArr.get(emptyArr.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲異常 (index == size 存取): " + e.getMessage());
        }

        try {
            emptyArr.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("捕獲異常 (空結構刪除): " + e.getMessage());
        }
    }
}
