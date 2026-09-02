package day0902;

import java.util.*;

public class DirectedReachability {
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addEdge(String u, String v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public boolean isReachable(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;
        if (from.equals(to)) return true;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(to)) return true;

            for (String neighbor : adjList.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }
}
