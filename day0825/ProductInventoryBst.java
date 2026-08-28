package day0825;

public class ProductInventoryBst {
    static class Product {
        int id;
        String name;
        int stock;

        Product(int id, String name, int stock) {
            this.id = id;
            this.name = name;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return String.format("[ID: %d | Name: %s | Stock: %d]", id, name, stock);
        }
    }

    static class Node {
        Product product;
        Node left, right;
        Node(Product product) { this.product = product; }
    }

    private Node root;

    public void addProduct(int id, String name, int stock) {
        root = insertRec(root, new Product(id, name, stock));
    }

    private Node insertRec(Node node, Product p) {
        if (node == null) return new Node(p);
        if (p.id < node.product.id) node.left = insertRec(node.left, p);
        else if (p.id > node.product.id) node.right = insertRec(node.right, p);
        else System.out.println("Product ID already exists.");
        return node;
    }

    public Product find(int id) {
        Node node = findNode(root, id);
        return node != null ? node.product : null;
    }

    private Node findNode(Node node, int id) {
        if (node == null || node.product.id == id) return node;
        if (id < node.product.id) return findNode(node.left, id);
        return findNode(node.right, id);
    }

    public void restock(int id, int qty) {
        Product p = find(id);
        if (p != null) p.stock += qty;
    }

    public void reduceStock(int id, int qty) {
        Product p = find(id);
        if (p != null && p.stock >= qty) p.stock -= qty;
    }

    public void delete(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node node, int id) {
        if (node == null) return null;
        if (id < node.product.id) node.left = deleteRec(node.left, id);
        else if (id > node.product.id) node.right = deleteRec(node.right, id);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node minNode = node.right;
            while (minNode.left != null) minNode = minNode.left;
            node.product = minNode.product;
            node.right = deleteRec(node.right, minNode.product.id);
        }
        return node;
    }

    public void inorderReport() {
        System.out.println("--- Inventory Inorder Report ---");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.product);
            inorderRec(node.right);
        }
    }

    public static void main(String[] args) {
        ProductInventoryBst inv = new ProductInventoryBst();
        inv.addProduct(50, "Laptop", 10);
        inv.addProduct(20, "Mouse", 50);
        inv.addProduct(70, "Keyboard", 30);

        inv.restock(20, 20);
        inv.reduceStock(50, 2);
        inv.inorderReport();

        inv.delete(20);
        inv.inorderReport();
    }
}
