package day0825;

import java.util.Arrays;

public class BstShapeExperiment {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    static class BST {
        Node root;

        void insert(int val) {
            root = insertRec(root, val);
        }

        private Node insertRec(Node node, int val) {
            if (node == null) return new Node(val);
            if (val < node.val) node.left = insertRec(node.left, val);
            else if (val > node.val) node.right = insertRec(node.right, val);
            return node;
        }

        int getHeight() {
            return getHeightRec(root);
        }

        private int getHeightRec(Node node) {
            if (node == null) return 0;
            return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
        }

        int totalSearchComparisons(int[] data) {
            int total = 0;
            for (int val : data) {
                total += getComparisons(val);
            }
            return total;
        }

        private int getComparisons(int val) {
            Node curr = root;
            int count = 0;
            while (curr != null) {
                count++;
                if (curr.val == val) return count;
                if (val < curr.val) curr = curr.left;
                else curr = curr.right;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        BST sortedTree = new BST();
        for (int v : data) sortedTree.insert(v);

        BST reverseTree = new BST();
        for (int i = data.length - 1; i >= 0; i--) reverseTree.insert(data[i]);

        BST balancedTree = new BST();
        int[] balancedOrder = {8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15};
        for (int v : balancedOrder) balancedTree.insert(v);

        System.out.printf("%-20s | %-10s | %-25s\n", "Insertion Order", "Height", "Total Search Comparisons");
        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-20s | %-10d | %-25d\n", "Sorted (Asc)", sortedTree.getHeight(), sortedTree.totalSearchComparisons(data));
        System.out.printf("%-20s | %-10d | %-25d\n", "Sorted (Desc)", reverseTree.getHeight(), reverseTree.totalSearchComparisons(data));
        System.out.printf("%-20s | %-10d | %-25d\n", "Balanced Order", balancedTree.getHeight(), balancedTree.totalSearchComparisons(data));
    }
}
