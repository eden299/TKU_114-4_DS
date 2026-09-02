package day0902;

import java.util.*;

public class DataStructureDecisionReport {
    public static void printDecisionReport() {
        String[][] decisions = {
            {"1. Fast lookup by ID", "HashMap", "O(1) average time complexity"},
            {"2. Maintain inserted order", "LinkedHashMap", "Doubly-linked list preserves order O(1)"},
            {"3. Maintain sorted key order", "TreeMap", "Red-Black tree provides O(log N)"},
            {"4. Priority-based retrieval", "PriorityQueue", "Min/Max heap provides O(1) peek, O(log N) pop"},
            {"5. Graph representation (Sparse)", "Adjacency List", "Saves space O(V + E)"},
            {"6. Graph representation (Dense)", "Adjacency Matrix", "Fast edge existence check O(1)"},
            {"7. Unweighted shortest path", "BFS + Queue", "Level-by-level traversal O(V + E)"},
            {"8. Unique elements check", "HashSet", "Hash-based duplicate prevention O(1)"},
            {"9. FIFO buffering", "Queue (LinkedList/ArrayDeque)", "O(1) offer and poll"},
            {"10. LIFO function calls/DFS", "Stack (ArrayDeque)", "O(1) push and pop"},
            {"11. Dynamic array storage", "ArrayList", "O(1) random access by index"},
            {"12. Key-value with range queries", "TreeMap", "O(log N) subMap and headMap"}
        };

        for (String[] row : decisions) {
            System.out.println("Requirement: " + row[0]);
            System.out.println("  Selection: " + row[1]);
            System.out.println("  Reason & Big-O: " + row[2]);
            System.out.println();
        }
    }
}
