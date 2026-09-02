package day0902;

import java.util.*;

public class BfsLayerReport {
    public static Map<String, Integer> getShortestEdgeCount(Map<String, List<String>> graph, String start) {
        Map<String, Integer> distances = new HashMap<>();
        if (graph == null || !graph.containsKey(start)) {
            return distances;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentDist = distances.get(current);

            List<String> neighbors = graph.getOrDefault(current, Collections.emptyList());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distances.put(neighbor, currentDist + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return distances;
    }
}
