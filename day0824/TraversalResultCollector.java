package day0824;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    String val;
    TreeNode left, right;
    TreeNode(String val) { this.val = val; }
}

public class TraversalResultCollector {

    public static List<String> preorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private static void preorderHelper(TreeNode root, List<String> result) {
        if (root == null) return;
        result.add(root.val);
        preorderHelper(root.left, result);
        preorderHelper(root.right, result);
    }

    public static List<String> inorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode root, List<String> result) {
        if (root == null) return;
        inorderHelper(root.left, result);
        result.add(root.val);
        inorderHelper(root.right, result);
    }

    public static List<String> postorder(TreeNode root) {
        List<String> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private static void postorderHelper(TreeNode root, List<String> result) {
        if (root == null) return;
        postorderHelper(root.left, result);
        postorderHelper(root.right, result);
        result.add(root.val);
    }

    public static List<String> levelOrder(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            result.add(curr.val);
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return result;
    }

    public static void testTree(String label, TreeNode root) {
        System.out.println("=== " + label + " ===");
        System.out.println("Preorder:   " + preorder(root));
        System.out.println("Inorder:    " + inorder(root));
        System.out.println("Postorder:  " + postorder(root));
        System.out.println("LevelOrder: " + levelOrder(root));
        System.out.println();
    }

    public static void main(String[] args) {
        testTree("Empty Tree", null);

        testTree("Single-Node Tree", new TreeNode("A"));

        TreeNode leftSkewed = new TreeNode("A");
        leftSkewed.left = new TreeNode("B");
        leftSkewed.left.left = new TreeNode("C");
        testTree("Left-Skewed Tree", leftSkewed);

        TreeNode complete = new TreeNode("A");
        complete.left = new TreeNode("B");
        complete.right = new TreeNode("C");
        complete.left.left = new TreeNode("D");
        complete.left.right = new TreeNode("E");
        testTree("Complete Tree", complete);
    }
}
