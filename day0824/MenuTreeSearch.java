package day0824;

import java.util.ArrayList;
import java.util.List;

class MenuNode {
    String name;
    List<MenuNode> children;

    MenuNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    void addChild(MenuNode child) {
        this.children.add(child);
    }
}

public class MenuTreeSearch {

    public static boolean contains(MenuNode root, String target) {
        if (root == null) return false;
        if (root.name.equals(target)) return true;
        for (MenuNode child : root.children) {
            if (contains(child, target)) return true;
        }
        return false;
    }

    public static int findDepth(MenuNode root, String target) {
        return depthHelper(root, target, 0);
    }

    private static int depthHelper(MenuNode root, String target, int currentDepth) {
        if (root == null) return -1;
        if (root.name.equals(target)) return currentDepth;
        for (MenuNode child : root.children) {
            int result = depthHelper(child, target, currentDepth + 1);
            if (result != -1) return result;
        }
        return -1;
    }

    public static int countLeaves(MenuNode root) {
        if (root == null) return 0;
        if (root.children.isEmpty()) return 1;
        int leaves = 0;
        for (MenuNode child : root.children) {
            leaves += countLeaves(child);
        }
        return leaves;
    }

    public static void displayPreorder(MenuNode root, String indent) {
        if (root == null) return;
        System.out.println(indent + "- " + root.name);
        for (MenuNode child : root.children) {
            displayPreorder(child, indent + "  ");
        }
    }

    public static void main(String[] args) {
        MenuNode root = new MenuNode("Main Menu");
        
        MenuNode file = new MenuNode("File");
        file.addChild(new MenuNode("New"));
        file.addChild(new MenuNode("Open"));

        MenuNode edit = new MenuNode("Edit");
        MenuNode copy = new MenuNode("Copy");
        edit.addChild(copy);
        edit.addChild(new MenuNode("Paste"));

        root.addChild(file);
        root.addChild(edit);

        System.out.println("=== Menu Display (Preorder) ===");
        displayPreorder(root, "");

        System.out.println("\n=== Functional Tests ===");
        System.out.println("Contains 'Copy': " + contains(root, "Copy"));
        System.out.println("Contains 'Delete': " + contains(root, "Delete"));
        
        System.out.println("Depth of 'Main Menu': " + findDepth(root, "Main Menu"));
        System.out.println("Depth of 'Copy': " + findDepth(root, "Copy"));
        System.out.println("Depth of 'Delete': " + findDepth(root, "Delete")); // 應回傳 -1
        
        System.out.println("Leaf Count: " + countLeaves(root));
    }
}
