package day0825;

public class BstDuplicateCounter {
    static class Node {
        int key;
        int count;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.count = 1;
        }
    }

    static class BST {
        Node root;

        void insert(int key) {
            root = insertRec(root, key);
        }

        private Node insertRec(Node node, int key) {
            if (node == null) return new Node(key);

            if (key == node.key) {
                node.count++;
            } else if (key < node.key) {
                node.left = insertRec(node.left, key);
            } else {
                node.right = insertRec(node.right, key);
            }
            return node;
        }

        void printInorder() {
            printInorderRec(root);
            System.out.println();
        }

        private void printInorderRec(Node node) {
            if (node != null) {
                printInorderRec(node.left);
                System.out.print(node.key + "(" + node.count + ") ");
                printInorderRec(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] data = {10, 5, 15, 5, 10, 20, 5, 15, 2};

        for (int val : data) {
            bst.insert(val);
        }

        System.out.print("Inorder Output key(count): ");
        bst.printInorder();
    }
}
