package day0825;

public class BstDeleteCases {
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

        void delete(int key) {
            root = deleteRec(root, key);
        }

        private Node deleteRec(Node node, int key) {
            if (node == null) return null;

            if (key < node.val) {
                node.left = deleteRec(node.left, key);
            } else if (key > node.val) {
                node.right = deleteRec(node.right, key);
            } else {
                if (node.left == null && node.right == null) {
                    return null;
                }
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;

                Node minNode = findMin(node.right);
                node.val = minNode.val;
                node.right = deleteRec(node.right, minNode.val);
            }
            return node;
        }

        private Node findMin(Node node) {
            while (node.left != null) node = node.left;
            return node;
        }

        int getSize() {
            return getSizeRec(root);
        }

        private int getSizeRec(Node node) {
            if (node == null) return 0;
            return 1 + getSizeRec(node.left) + getSizeRec(node.right);
        }

        boolean isValidBST() {
            return isValidBSTRec(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isValidBSTRec(Node node, long min, long max) {
            if (node == null) return true;
            if (node.val <= min || node.val >= max) return false;
            return isValidBSTRec(node.left, min, node.val) && isValidBSTRec(node.right, node.val, max);
        }

        void printStatus(String label) {
            System.out.println("[" + label + "]");
            System.out.print("Inorder: ");
            printInorder(root);
            System.out.println("\nSize: " + getSize());
            System.out.println("Valid BST Result: " + isValidBST());
            System.out.println("------------------------------------");
        }

        private void printInorder(Node node) {
            if (node != null) {
                printInorder(node.left);
                System.out.print(node.val + " ");
                printInorder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        int[] keys = {50, 30, 70, 20, 40, 60, 80, 45};
        for (int k : keys) bst.insert(k);

        bst.printStatus("Initial State");

        bst.delete(20);
        bst.printStatus("After deleting Leaf node (20)");

        bst.delete(40);
        bst.printStatus("After deleting Single-child node (40)");

        bst.delete(50);
        bst.printStatus("After deleting Two-child node (50)");
    }
}
