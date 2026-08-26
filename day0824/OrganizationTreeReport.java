package day0824;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class OrgNode {
    String name;
    OrgNode left, right;
    OrgNode(String name) { this.name = name; }
}

public class OrganizationTreeReport {

    public static String findParent(OrgNode root, String target) {
        if (root == null || target == null || root.name.equals(target)) return null;
        return findParentHelper(root, target);
    }

    private static String findParentHelper(OrgNode root, String target) {
        if (root == null) return null;
        if ((root.left != null && root.left.name.equals(target)) || 
            (root.right != null && root.right.name.equals(target))) {
            return root.name;
        }
        String leftRes = findParentHelper(root.left, target);
        if (leftRes != null) return leftRes;
        return findParentHelper(root.right, target);
    }

    public static int findDepth(OrgNode root, String target) {
        return findDepthHelper(root, target, 0);
    }

    private static int findDepthHelper(OrgNode root, String target, int currentDepth) {
        if (root == null) return -1;
        if (root.name.equals(target)) return currentDepth;
        
        int leftDepth = findDepthHelper(root.left, target, currentDepth + 1);
        if (leftDepth != -1) return leftDepth;
        
        return findDepthHelper(root.right, target, currentDepth + 1);
    }

    public static List<String> pathFromRoot(OrgNode root, String target) {
        List<String> path = new ArrayList<>();
        if (root == null || target == null) return Collections.emptyList();
        if (findPathHelper(root, target, path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private static boolean findPathHelper(OrgNode root, String target, List<String> path) {
        if (root == null) return false;
        path.add(root.name);
        if (root.name.equals(target)) return true;

        if (findPathHelper(root.left, target, path) || findPathHelper(root.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static void printByLevel(OrgNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        Queue<OrgNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Level " + level + ": ");
            for (int i = 0; i < levelSize; i++) {
                OrgNode curr = queue.poll();
                System.out.print(curr.name + " ");
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        OrgNode CEO = new OrgNode("CEO");
        CEO.left = new OrgNode("VP-Tech");
        CEO.right = new OrgNode("VP-Sales");
        
        CEO.left.left = new OrgNode("Dev-Lead");
        CEO.left.right = new OrgNode("QA-Lead");

        System.out.println("=== Level-By-Level Hierarchy ===");
        printByLevel(CEO);

        System.out.println("\n=== Query Tests ===");
        System.out.println("Parent of 'Dev-Lead': " + findParent(CEO, "Dev-Lead"));
        System.out.println("Parent of 'CEO': " + findParent(CEO, "CEO"));
        System.out.println("Parent of 'Unknown': " + findParent(CEO, "Unknown"));

        System.out.println("Depth of 'QA-Lead': " + findDepth(CEO, "QA-Lead"));
        System.out.println("Depth of 'Unknown': " + findDepth(CEO, "Unknown"));

        System.out.println("Path to 'Dev-Lead': " + pathFromRoot(CEO, "Dev-Lead"));
        System.out.println("Path to 'Unknown': " + pathFromRoot(CEO, "Unknown"));
    }
}
