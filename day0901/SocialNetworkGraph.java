package day0901;

import java.util.*;

public class SocialNetworkGraph {
    private Map<String, Set<String>> adjList;

    public SocialNetworkGraph() {
        this.adjList = new HashMap<>();
    }

    public void addUser(String user) {
        adjList.putIfAbsent(user, new HashSet<>());
    }

    public void addFriendship(String u, String v) {
        addUser(u);
        addUser(v);
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public void removeFriendship(String u, String v) {
        if (adjList.containsKey(u)) adjList.get(u).remove(v);
        if (adjList.containsKey(v)) adjList.get(v).remove(u);
    }

    public Set<String> getCommonFriends(String u, String v) {
        if (!adjList.containsKey(u) || !adjList.containsKey(v)) return Collections.emptySet();
        Set<String> common = new HashSet<>(adjList.get(u));
        common.retainAll(adjList.get(v));
        return common;
    }

    public List<String> getIsolatedUsers() {
        List<String> isolated = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
            if (entry.getValue().isEmpty()) {
                isolated.add(entry.getKey());
            }
        }
        return isolated;
    }
}
