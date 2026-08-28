package day0825;

public class BstSearchTrace {
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

        void searchWithTrace(int target) {
            System.out.println("--- Search Trace for target: " + target + " ---");
            Node curr = root;
            int count = 0;
            boolean found = false;

            while (curr != null) {
                count++;
                if (curr.val == target) {
                    System.out.printf("Comparison %d: Current = %d, Match Found!\n", count, curr.val);
                    found = true;
                    break;
                } else if (target < curr.val) {
                    System.out.printf("Comparison %d: Current = %d, Target %d < %d -> Go LEFT\n", 
                                      count, curr.val, target, curr.val);
                    curr = curr.left;
                } else {
                    System.out.printf("Comparison %d: Current = %d, Target %d > %d -> Go RIGHT\n", 
                                      count, curr.val, target, curr.val);
                    curr = curr.right;
                }
            }

            if (!found) {
                System.out.printf("Target %d Not Found! Total comparisons: %d\n", target, count);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        int[] keys = {10, 5, 15, 2, 7};
        for (int k : keys) bst.insert(k);

        bst.searchWithTrace(10);
        bst.searchWithTrace(5);
        bst.searchWithTrace(7);
        bst.searchWithTrace(12);
    }
}
