package day0825;

public class BstDeleteTestSuite {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    private Node root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private Node insertRec(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else if (val > node.val) node.right = insertRec(node.right, val);
        return node;
    }

    public boolean delete(int key) {
        int oldSize = size();
        root = deleteRec(root, key);
        return size() < oldSize;
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) return null;
        if (key < node.val) node.left = deleteRec(node.left, key);
        else if (key > node.val) node.right = deleteRec(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node min = node.right;
            while (min.left != null) min = min.left;
            node.val = min.val;
            node.right = deleteRec(node.right, min.val);
        }
        return node;
    }

    public int size() { return sizeRec(root); }
    private int sizeRec(Node node) {
        return node == null ? 0 : 1 + sizeRec(node.left) + sizeRec(node.right);
    }

    public void printState(String msg) {
        System.out.printf("%-35s | Size: %d | Root: %s\n", msg, size(), (root == null ? "null" : root.val));
    }

    public static void main(String[] args) {
        BstDeleteTestSuite tree = new BstDeleteTestSuite();

        System.out.println("Delete from Empty: " + tree.delete(10));
        tree.printState("After Empty Delete");

        tree.insert(50);
        tree.printState("Single Root Tree");
        tree.delete(50);
        tree.printState("After Single Root Delete");

        tree.insert(50);
        tree.insert(30);
        tree.printState("Root with One Child (Left)");
        tree.delete(50);
        tree.printState("After Deleting Root(50)");

        System.out.println("Delete Missing Key 99: " + tree.delete(99));

        tree = new BstDeleteTestSuite();
        int[] vals = {50, 20, 70, 10, 30};
        for (int v : vals) tree.insert(v);
        tree.printState("Root with Two Children");

        System.out.println("--- Continuous Deletion ---");
        for (int v : vals) {
            tree.delete(v);
            tree.printState("Deleted " + v);
        }
    }
}
