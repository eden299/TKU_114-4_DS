package day0824;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class LevelOrderByLine {

    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + " (Nodes: " + levelSize + "): ");

            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();
                System.out.print(curr.val + " ");

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Empty Tree ---");
        printLevelOrder(null);

        System.out.println("\n--- Test Binary Tree ---");
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(50);
        root.right.right = new TreeNode(60);

        printLevelOrder(root);
    }
}
