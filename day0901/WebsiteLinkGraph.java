package day0901;

import java.util.*;

public class WebsiteLinkGraph {
    private Map<String, Set<String>> adjList = new HashMap<>();
    private Set<String> pages = new HashSet<>();

    public void addPage(String page) {
        pages.add(page);
        adjList.putIfAbsent(page, new HashSet<>());
    }

    public void addLink(String fromPage, String toPage) {
        addPage(fromPage);
        addPage(toPage);
        adjList.get(fromPage).add(toPage);
    }

    public Set<String> getOutgoingLinks(String page) {
        return adjList.getOrDefault(page, Collections.emptySet());
    }

    public int getIncomingCount(String page) {
        int count = 0;
        for (Set<String> links : adjList.values()) {
            if (links.contains(page)) {
                count++;
            }
        }
        return count;
    }

    public List<String> getPagesWithNoIncoming() {
        List<String> result = new ArrayList<>();
        for (String page : pages) {
            if (getIncomingCount(page) == 0) {
                result.add(page);
            }
        }
        return result;
    }

    public List<String> getPagesWithNoOutgoing() {
        List<String> result = new ArrayList<>();
        for (String page : pages) {
            if (adjList.getOrDefault(page, Collections.emptySet()).isEmpty()) {
                result.add(page);
            }
        }
        return result;
    }
}
