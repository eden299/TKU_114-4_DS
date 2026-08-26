package day0824;

import java.util.NoSuchElementException;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BinaryTreeStatistics {

    public static int size(TreeNode root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    public static int sum(TreeNode root) {
        if (root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    public static int maximum(TreeNode root) {
        if (root == null) {
            throw new NoSuchElementException("Cannot find maximum of an empty tree.");
        }
        int max = root.val;
        if (root.left != null) max = Math.max(max, maximum(root.left));
        if (root.right != null) max = Math.max(max, maximum(root.right));
        return max;
    }

    public static int leafCount(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return leafCount(root.left) + leafCount(root.right);
    }

    public static int height(TreeNode root) {
        if (root == null) return -1;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static boolean contains(TreeNode root, int target) {
        if (root == null) return false;
        if (root.val == target) return true;
        return contains(root.left, target) || contains(root.right, target);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(30);

        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Maximum: " + maximum(root));
        System.out.println("Leaf Count: " + leafCount(root));
        System.out.println("Height: " + height(root));
        System.out.println("Contains 20: " + contains(root, 20));
        System.out.println("Contains 99: " + contains(root, 99));

        try {
            maximum(null);
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception for empty tree: " + e.getMessage());
        }
    }
}
