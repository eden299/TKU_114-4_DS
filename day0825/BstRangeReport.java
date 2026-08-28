package day0825;

public class BstRangeReport {
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

        Integer findMin() {
            if (root == null) return null;
            Node curr = root;
            while (curr.left != null) curr = curr.left;
            return curr.val;
        }

        Integer findMax() {
            if (root == null) return null;
            Node curr = root;
            while (curr.right != null) curr = curr.right;
            return curr.val;
        }

        void printRange(int low, int high) {
            System.out.printf("Range [%d, %d]: ", low, high);
            if (low > high) {
                System.out.println("Invalid range! (low > high)");
                return;
            }
            printRangeRec(root, low, high);
            System.out.println();
        }

        private void printRangeRec(Node node, int low, int high) {
            if (node == null) return;

            if (node.val > low) {
                printRangeRec(node.left, low, high);
            }
            if (node.val >= low && node.val <= high) {
                System.out.print(node.val + " ");
            }
            if (node.val < high) {
                printRangeRec(node.right, low, high);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] keys = {20, 10, 30, 5, 15, 25, 35};
        for (int k : keys) bst.insert(k);

        System.out.println("Min value: " + bst.findMin());
        System.out.println("Max value: " + bst.findMax());

        bst.printRange(10, 30);
        bst.printRange(5, 15);
        bst.printRange(30, 10);
    }
}
