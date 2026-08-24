package day0820;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StoreProduct implements Comparable<StoreProduct> {
    private int id;
    private String name;
    private double price;
    private int stock;

    public StoreProduct(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    // 1. Natural order 依 id 升冪
    @Override
    public int compareTo(StoreProduct other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("StoreProduct{id=%d, name='%s', price=%.1f, stock=%d}", id, name, price, stock);
    }
}

public class ProductComparatorPractice {
    public static void main(String[] args) {
        List<StoreProduct> products = new ArrayList<>();
        products.add(new StoreProduct(3, "Keyboard", 1500.0, 50));
        products.add(new StoreProduct(1, "Mouse", 800.0, 100));
        products.add(new StoreProduct(5, "Monitor", 5000.0, 50));
        products.add(new StoreProduct(2, "Headset", 1500.0, 30));
        products.add(new StoreProduct(4, "Webcam", 1200.0, 100));

        System.out.println("=== 原始順序 ===");
        products.forEach(System.out::println);

        List<StoreProduct> list1 = new ArrayList<>(products);
        Collections.sort(list1);
        System.out.println("\n=== Natural Order (id 升冪) ===");
        list1.forEach(System.out::println);

        List<StoreProduct> list2 = new ArrayList<>(products);
        Comparator<StoreProduct> priceThenNameComp = Comparator
                .comparingDouble(StoreProduct::getPrice)
                .thenComparing(StoreProduct::getName);
        list2.sort(priceThenNameComp);
        System.out.println("\n=== Comparator 1 (price 升冪，同價依 name) ===");
        list2.forEach(System.out::println);

        List<StoreProduct> list3 = new ArrayList<>(products);
        Comparator<StoreProduct> stockThenIdComp = Comparator
                .comparingInt(StoreProduct::getStock).reversed()
                .thenComparingInt(StoreProduct::getId);
        list3.sort(stockThenIdComp);
        System.out.println("\n=== Comparator 2 (stock 降冪，同庫存依 id) ===");
        list3.forEach(System.out::println);
    }
}
