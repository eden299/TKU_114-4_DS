package day0825;

public class OrderBstSystem {
    static class Order {
        int orderId;
        String customerName;
        double amount;

        Order(int orderId, String customerName, double amount) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return String.format("[OrderID: %d | Customer: %s | Amount: $%.2f]", orderId, customerName, amount);
        }
    }

    static class Node {
        Order order;
        Node left, right;
        Node(Order order) { this.order = order; }
    }

    private Node root;

    public void add(int orderId, String customerName, double amount) {
        root = insertRec(root, new Order(orderId, customerName, amount));
    }

    private Node insertRec(Node node, Order order) {
        if (node == null) return new Node(order);
        if (order.orderId < node.order.orderId) node.left = insertRec(node.left, order);
        else if (order.orderId > node.order.orderId) node.right = insertRec(node.right, order);
        return node;
    }

    public Order find(int orderId) {
        Node curr = root;
        while (curr != null) {
            if (curr.order.orderId == orderId) return curr.order;
            if (orderId < curr.order.orderId) curr = curr.left;
            else curr = curr.right;
        }
        return null;
    }

    public boolean updateAmount(int orderId, double newAmount) {
        Order o = find(orderId);
        if (o != null) {
            o.amount = newAmount;
            return true;
        }
        return false;
    }

    public void cancel(int orderId) {
        root = deleteRec(root, orderId);
    }

    private Node deleteRec(Node node, int orderId) {
        if (node == null) return null;
        if (orderId < node.order.orderId) node.left = deleteRec(node.left, orderId);
        else if (orderId > node.order.orderId) node.right = deleteRec(node.right, orderId);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = node.right;
            while (min.left != null) min = min.left;
            node.order = min.order;
            node.right = deleteRec(node.right, min.order.orderId);
        }
        return node;
    }

    public void rangeReport(int startId, int endId) {
        System.out.printf("--- Range Report [%d - %d] ---\n", startId, endId);
        rangeRec(root, startId, endId);
        System.out.println();
    }

    private void rangeRec(Node node, int start, int end) {
        if (node == null) return;
        if (node.order.orderId > start) rangeRec(node.left, start, end);
        if (node.order.orderId >= start && node.order.orderId <= end) {
            System.out.println(node.order);
        }
        if (node.order.orderId < end) rangeRec(node.right, start, end);
    }

    public void summary() {
        int count = getCount(root);
        double totalAmt = getTotalAmount(root);
        System.out.println("=== System Summary ===");
        System.out.println("Total Orders: " + count);
        System.out.printf("Total Amount: $%.2f\n", totalAmt);
        System.out.printf("Average Amount: $%.2f\n", count == 0 ? 0 : totalAmt / count);
    }

    private int getCount(Node node) {
        return node == null ? 0 : 1 + getCount(node.left) + getCount(node.right);
    }

    private double getTotalAmount(Node node) {
        return node == null ? 0 : node.order.amount + getTotalAmount(node.left) + getTotalAmount(node.right);
    }

    public static void main(String[] args) {
        OrderBstSystem sys = new OrderBstSystem();
        sys.add(105, "Alice", 250.0);
        sys.add(101, "Bob", 120.5);
        sys.add(108, "Charlie", 300.0);
        sys.add(103, "David", 75.0);

        sys.updateAmount(101, 150.0);
        sys.rangeReport(101, 105);
        
        sys.cancel(105);
        sys.summary();
    }
}
