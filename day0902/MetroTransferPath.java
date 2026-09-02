package day0902;

import java.util.*;

public class MetroTransferPath {
    public static class PathResult {
        public List<String> path;
        public int edgeCount;

        public PathResult(List<String> path, int edgeCount) {
            this.path = path;
            this.edgeCount = edgeCount;
        }
    }

    public static PathResult findShortestPath(Map<String, List<String>> graph, String start, String target) {
        if (graph == null || !graph.containsKey(start) || !graph.containsKey(target)) {
            return new PathResult(Collections.emptyList(), -1);
        }
        if (start.equals(target)) {
            return new PathResult(Collections.singletonList(start), 0);
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> predecessor = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(target)) {
                found = true;
                break;
            }

            for (String neighbor : graph.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessor.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!found && !predecessor.containsKey(target)) {
            return new PathResult(Collections.emptyList(), -1);
        }

        List<String> path = new ArrayList<>();
        String curr = target;
        while (curr != null) {
            path.add(curr);
            curr = predecessor.get(curr);
        }
        Collections.reverse(path);
        return new PathResult(path, path.size() - 1);
    }
}
