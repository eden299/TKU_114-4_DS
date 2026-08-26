package day0824;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class BinaryTreeStructureReport {

    public static int size(TreeNode root) {
        if (root == null) return 0;
        return 1 + size(root.left) + size(root.right);
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

    public static void printLeaves(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            System.out.print(root.val + " ");
            return;
        }
        printLeaves(root.left);
        printLeaves(root.right);
    }

    public static void printReport(String name, TreeNode root) {
        System.out.println("=== " + name + " ===");
        System.out.println("Root: " + (root == null ? "null" : root.val));
        System.out.print("Leaves: ");
        if (root == null) System.out.print("None");
        else printLeaves(root);
        System.out.println();
        System.out.println("Size: " + size(root));
        System.out.println("Leaf Count: " + leafCount(root));
        System.out.println("Height: " + height(root));
        System.out.println();
    }

    public static void main(String[] args) {
        printReport("Empty Tree", null);

        printReport("Single-node Tree", new TreeNode(10));

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        printReport("7-Node Tree", root);
    }
}
