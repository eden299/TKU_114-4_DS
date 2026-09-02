package day0901;

import java.util.*;

public class LogisticsWeightedGraph {
    public static class Edge {
        private String target;
        private double weight;

        public Edge(String target, double weight) {
            this.target = target;
            this.weight = weight;
        }

        public String getTarget() { return target; }
        public double getWeight() { return weight; }
        public void setWeight(double weight) { this.weight = weight; }
    }

    private Map<String, List<Edge>> adjList = new HashMap<>();

    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public boolean addOrUpdateEdge(String from, String to, double weight) {
        if (weight < 0) return false;
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) return false;

        List<Edge> edges = adjList.get(from);
        for (Edge edge : edges) {
            if (edge.getTarget().equals(to)) {
                edge.setWeight(weight);
                return true;
            }
        }
        edges.add(new Edge(to, weight));
        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (!adjList.containsKey(from)) return false;
        List<Edge> edges = adjList.get(from);
        return edges.removeIf(edge -> edge.getTarget().equals(to));
    }

    public Double getWeight(String from, String to) {
        if (!adjList.containsKey(from)) return null;
        for (Edge edge : adjList.get(from)) {
            if (edge.getTarget().equals(to)) {
                return edge.getWeight();
            }
        }
        return null;
    }

    public List<Edge> getNeighbors(String vertex) {
        return adjList.getOrDefault(vertex, Collections.emptyList());
    }
}
