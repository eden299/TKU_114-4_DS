package day0824;

import java.util.ArrayList;
import java.util.List;

class FolderNode {
    String name;
    int ownSize;
    FolderNode left, right;

    FolderNode(String name, int ownSize) {
        this.name = name;
        this.ownSize = ownSize;
    }
}

public class FolderSizeTree {

    public static int calculateSubtreeSize(FolderNode root) {
        if (root == null) return 0;
        int leftSize = calculateSubtreeSize(root.left);
        int rightSize = calculateSubtreeSize(root.right);
        return root.ownSize + leftSize + rightSize;
    }

    public static FolderNode findMaxSubtree(FolderNode root) {
        if (root == null) return null;
        FolderNode[] maxNodeHolder = new FolderNode[]{root};
        int[] maxValHolder = new int[]{calculateSubtreeSize(root)};
        findMaxSubtreeHelper(root, maxNodeHolder, maxValHolder);
        return maxNodeHolder[0];
    }

    private static void findMaxSubtreeHelper(FolderNode root, FolderNode[] maxNode, int[] maxVal) {
        if (root == null) return;
        int currentSize = calculateSubtreeSize(root);
        if (currentSize > maxVal[0]) {
            maxVal[0] = currentSize;
            maxNode[0] = root;
        }
        findMaxSubtreeHelper(root.left, maxNode, maxVal);
        findMaxSubtreeHelper(root.right, maxNode, maxVal);
    }

    public static List<String> getLeafFolders(FolderNode root) {
        List<String> leaves = new ArrayList<>();
        getLeafFoldersHelper(root, leaves);
        return leaves;
    }

    private static void getLeafFoldersHelper(FolderNode root, List<String> leaves) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            leaves.add(root.name);
            return;
        }
        getLeafFoldersHelper(root.left, leaves);
        getLeafFoldersHelper(root.right, leaves);
    }

    public static void main(String[] args) {
        FolderNode root = new FolderNode("Root", 100);
        root.left = new FolderNode("Documents", 50);
        root.right = new FolderNode("Pictures", 200);

        root.left.left = new FolderNode("Work", 500);
        root.left.right = new FolderNode("Personal", 300);

        root.right.right = new FolderNode("Vacation", 400);

        System.out.println("Total Size: " + calculateSubtreeSize(root) + " KB");
        
        FolderNode maxFolder = findMaxSubtree(root);
        System.out.println("Max Subtree Root: " + (maxFolder != null ? maxFolder.name : "None") + 
                           " (Size: " + calculateSubtreeSize(maxFolder) + " KB)");
                           
        System.out.println("Leaf Folders: " + getLeafFolders(root));
    }
}
