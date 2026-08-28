package day0825;

public class BstInvariantChecker {
    static class Node {
        int val;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) {
            return false;
        }

        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static void main(String[] args) {

        Node validTree = new Node(10);
        validTree.left = new Node(5);
        validTree.right = new Node(15);

        System.out.println("Valid Tree Verification: " + isValidBST(validTree)); 

        Node invalidTree1 = new Node(10);
        invalidTree1.left = new Node(12);

        System.out.println("Invalid Tree 1 Verification: " + isValidBST(invalidTree1)); 

        Node invalidTree2 = new Node(10);
        invalidTree2.right = new Node(15);
        invalidTree2.right.left = new Node(8);

        System.out.println("Invalid Tree 2 Verification: " + isValidBST(invalidTree2)); 

        Node invalidTree3 = new Node(20);
        invalidTree3.left = new Node(10);
        invalidTree3.right = new Node(30);
        invalidTree3.left.right = new Node(25);

        System.out.println("Invalid Tree 3 (Deep Violation) Verification: " + isValidBST(invalidTree3));
    }
}
