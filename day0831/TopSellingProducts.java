package day0831;

import java.util.*;

public class TopSellingProducts {
    public static class Product {
        String id;
        int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        @Override
        public String toString() {
            return id + ":" + sales;
        }
    }

    public static List<Product> getTopKProducts(List<Product> inputList, int k) {
        if (k <= 0 || inputList == null) return new ArrayList<>();

        Map<String, Integer> mergedSales = new HashMap<>();
        for (Product p : inputList) {
            if (p != null && p.id != null) {
                mergedSales.put(p.id, mergedSales.getOrDefault(p.id, 0) + p.sales);
            }
        }

        PriorityQueue<Product> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.sales != b.sales) {
                return Integer.compare(a.sales, b.sales);
            }
            return b.id.compareTo(a.id);
        });

        for (Map.Entry<String, Integer> entry : mergedSales.entrySet()) {
            Product p = new Product(entry.getKey(), entry.getValue());
            if (minHeap.size() < k) {
                minHeap.add(p);
            } else {
                if (compareProducts(p, minHeap.peek()) > 0) {
                    minHeap.poll();
                    minHeap.add(p);
                }
            }
        }

        List<Product> result = new ArrayList<>(minHeap);
        result.sort((a, b) -> {
            if (a.sales != b.sales) {
                return Integer.compare(b.sales, a.sales);
            }
            return a.id.compareTo(b.id);
        });

        return result;
    }

    private static int compareProducts(Product a, Product b) {
        if (a.sales != b.sales) {
            return Integer.compare(a.sales, b.sales);
        }
        return b.id.compareTo(a.id);
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("A", 100),
            new Product("B", 200),
            new Product("A", 150),
            new Product("C", 250),
            new Product("D", 250)
        );

        List<Product> topK = getTopKProducts(products, 3);
        System.out.println("Top products: " + topK);
    }
}
