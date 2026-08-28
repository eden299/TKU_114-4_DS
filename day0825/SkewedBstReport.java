package day0825;

public class SkewedBstReport {
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

        int getSize() {
            return getSizeRec(root);
        }

        private int getSizeRec(Node node) {
            if (node == null) return 0;
            return 1 + getSizeRec(node.left) + getSizeRec(node.right);
        }

        int getHeight() {
            return getHeightRec(root);
        }

        private int getHeightRec(Node node) {
            if (node == null) return 0;
            return 1 + Math.max(getHeightRec(node.left), getHeightRec(node.right));
        }

        int getSearchComparisons(int target) {
            Node curr = root;
            int count = 0;
            while (curr != null) {
                count++;
                if (curr.val == target) return count;
                if (target < curr.val) curr = curr.left;
                else curr = curr.right;
            }
            return count;
        }
    }

    public static void main(String[] args) {
        int[] sortedData = {1, 2, 3, 4, 5, 6, 7};

        int[] balancedData = {4, 2, 6, 1, 3, 5, 7};

        BST skewedTree = new BST();
        for (int v : sortedData) skewedTree.insert(v);

        BST balancedTree = new BST();
        for (int v : balancedData) balancedTree.insert(v);

        int target = 7;

        System.out.println("=== Skewed Tree (From Sorted Data) ===");
        System.out.println("Size: " + skewedTree.getSize());
        System.out.println("Height: " + skewedTree.getHeight());
        System.out.println("Search comparisons for (" + target + "): " + skewedTree.getSearchComparisons(target));

        System.out.println("\n=== Balanced Tree (From Balanced Order) ===");
        System.out.println("Size: " + balancedTree.getSize());
        System.out.println("Height: " + balancedTree.getHeight());
        System.out.println("Search comparisons for (" + target + "): " + balancedTree.getSearchComparisons(target));
    }
}
