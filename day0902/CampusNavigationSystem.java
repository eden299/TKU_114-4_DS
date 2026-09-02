package day0902;

import java.util.*;

public class CampusNavigationSystem {
    public static class Location {
        private String id;
        private String name;

        public Location(String id, String name) {
            this.id = id;
            this.name = name;
        }
        public String getId() { return id; }
        public String getName() { return name; }
    }

    private Map<String, Location> locations = new HashMap<>();
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addLocation(String id, String name) {
        locations.put(id, new Location(id, name));
        adjList.putIfAbsent(id, new ArrayList<>());
    }

    public void addRoad(String id1, String id2) {
        if (locations.containsKey(id1) && locations.containsKey(id2)) {
            adjList.get(id1).add(id2);
            adjList.get(id2).add(id1);
        }
    }

    public List<String> findShortestPath(String startId, String targetId) {
        if (!locations.containsKey(startId) || !locations.containsKey(targetId)) {
            return Collections.emptyList();
        }
        if (startId.equals(targetId)) {
            return Collections.singletonList(locations.get(startId).getName());
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.offer(startId);
        visited.add(startId);

        boolean found = false;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (curr.equals(targetId)) {
                found = true;
                break;
            }
            for (String neighbor : adjList.getOrDefault(curr, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, curr);
                    queue.offer(neighbor);
                }
            }
        }

        if (!found && !parent.containsKey(targetId)) {
            return Collections.emptyList();
        }

        List<String> path = new ArrayList<>();
        String curr = targetId;
        while (curr != null) {
            path.add(locations.get(curr).getName());
            curr = parent.get(curr);
        }
        Collections.reverse(path);
        return path;
    }
}
