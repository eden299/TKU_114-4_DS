package day0820;

import java.util.ArrayList;
import java.util.List;

class Repository<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        if (index < 0 || index >= items.size()) return null;
        return items.get(index);
    }

    public boolean remove(T item) {
        return items.remove(item);
    }

    public int size() {
        return items.size();
    }

    public void printAll() {
        System.out.println("Repository 內容: " + items);
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

public class GenericRepositorySystem {
    public static void main(String[] args) {
        Repository<String> stringRepo = new Repository<>();
        stringRepo.add("Java");
        stringRepo.add("Python");
        stringRepo.add("C++");
        System.out.println("=== Repository<String> ===");
        stringRepo.printAll();
        System.out.println("Size: " + stringRepo.size());
        System.out.println("Index 1: " + stringRepo.get(1));
        stringRepo.remove("Python");
        stringRepo.printAll();

        Repository<Product> productRepo = new Repository<>();
        productRepo.add(new Product("Laptop", 35000));
        productRepo.add(new Product("Mouse", 800));
        System.out.println("\n=== Repository<Product> ===");
        productRepo.printAll();
        System.out.println("Size: " + productRepo.size());
    }
}
