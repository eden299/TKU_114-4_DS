package day0902;

// IterativeDfsTrace.java
import java.util.*;

public class IterativeDfsTrace {
    public static void traceDfs(Map<String, List<String>> graph, String start) {
        if (graph == null || !graph.containsKey(start)) {
            System.out.println("Invalid start vertex or empty graph.");
            return;
        }

        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new LinkedHashSet<>();

        stack.push(start);
        System.out.println("PUSH: " + start + " | Stack: " + stack + " | Visited: " + visited);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.println("POP: " + current + " | Stack: " + stack + " | Visited: " + visited);

            if (!visited.contains(current)) {
                visited.add(current);
                List<String> neighbors = graph.getOrDefault(current, Collections.emptyList());
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    String neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                        System.out.println("PUSH: " + neighbor + " | Stack: " + stack + " | Visited: " + visited);
                    }
                }
            }
        }
    }
}
