package day0824;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalTestReport {

    static class TreeNode {
        String val;
        TreeNode left, right;

        TreeNode(String val) {
            this.val = val;
        }
    }

    public static List<String> preorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        preorderHelper(root, res);
        return res;
    }

    private static void preorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        res.add(root.val);
        preorderHelper(root.left, res);
        preorderHelper(root.right, res);
    }

    public static List<String> inorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        inorderHelper(root.left, res);
        res.add(root.val);
        inorderHelper(root.right, res);
    }

    public static List<String> postorder(TreeNode root) {
        List<String> res = new ArrayList<>();
        postorderHelper(root, res);
        return res;
    }

    private static void postorderHelper(TreeNode root, List<String> res) {
        if (root == null) return;
        postorderHelper(root.left, res);
        postorderHelper(root.right, res);
        res.add(root.val);
    }

    public static List<String> levelOrder(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            res.add(curr.val);
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }
        return res;
    }

    public static void runTestCase(String name, TreeNode root, 
                                  List<String> expPre, List<String> expIn, 
                                  List<String> expPost, List<String> expLevel) {
        System.out.println("==================================================");
        System.out.println("Test Case: " + name);
        
        List<String> actPre = preorder(root);
        List<String> actIn = inorder(root);
        List<String> actPost = postorder(root);
        List<String> actLevel = levelOrder(root);

        boolean preMatch = actPre.equals(expPre);
        boolean inMatch = actIn.equals(expIn);
        boolean postMatch = actPost.equals(expPost);
        boolean levelMatch = actLevel.equals(expLevel);

        System.out.println("Preorder:   Expected " + expPre + " | Actual " + actPre + " -> " + (preMatch ? "PASS" : "FAIL"));
        System.out.println("Inorder:    Expected " + expIn + " | Actual " + actIn + " -> " + (inMatch ? "PASS" : "FAIL"));
        System.out.println("Postorder:  Expected " + expPost + " | Actual " + actPost + " -> " + (postMatch ? "PASS" : "FAIL"));
        System.out.println("LevelOrder: Expected " + expLevel + " | Actual " + actLevel + " -> " + (levelMatch ? "PASS" : "FAIL"));
        
        boolean allPass = preMatch && inMatch && postMatch && levelMatch;
        System.out.println("OVERALL RESULT: " + (allPass ? "ALL PASSED" : "FAILED"));
    }

    public static void main(String[] args) {
        runTestCase("1. Empty Tree", null, 
                Arrays.asList(), Arrays.asList(), Arrays.asList(), Arrays.asList());

        runTestCase("2. Single-Node Tree", new TreeNode("A"), 
                Arrays.asList("A"), Arrays.asList("A"), Arrays.asList("A"), Arrays.asList("A"));

        TreeNode onlyLeft = new TreeNode("A");
        onlyLeft.left = new TreeNode("B");
        runTestCase("3. Only-Left Tree", onlyLeft, 
                Arrays.asList("A", "B"), Arrays.asList("B", "A"), Arrays.asList("B", "A"), Arrays.asList("A", "B"));

        TreeNode onlyRight = new TreeNode("A");
        onlyRight.right = new TreeNode("C");
        runTestCase("4. Only-Right Tree", onlyRight, 
                Arrays.asList("A", "C"), Arrays.asList("A", "C"), Arrays.asList("C", "A"), Arrays.asList("A", "C"));

        TreeNode complete = new TreeNode("A");
        complete.left = new TreeNode("B");
        complete.right = new TreeNode("C");
        runTestCase("5. Complete Tree", complete, 
                Arrays.asList("A", "B", "C"), Arrays.asList("B", "A", "C"), Arrays.asList("B", "C", "A"), Arrays.asList("A", "B", "C"));

        TreeNode irregular = new TreeNode("A");
        irregular.left = new TreeNode("B");
        irregular.left.right = new TreeNode("D");
        irregular.right = new TreeNode("C");
        runTestCase("6. Irregular Tree", irregular, 
                Arrays.asList("A", "B", "D", "C"), Arrays.asList("B", "D", "A", "C"), Arrays.asList("D", "B", "C", "A"), Arrays.asList("A", "B", "C", "D"));
    }
}
