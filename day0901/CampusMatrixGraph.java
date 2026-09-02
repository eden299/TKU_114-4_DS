package day0901;

import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {
    private boolean[][] adjMatrix;
    private int numVertices;

    public CampusMatrixGraph(int numVertices) {
        this.numVertices = numVertices;
        this.adjMatrix = new boolean[numVertices][numVertices];
    }

    public void addEdge(int u, int v) {
        if (u >= 0 && u < numVertices && v >= 0 && v < numVertices && u != v) {
            adjMatrix[u][v] = true;
            adjMatrix[v][u] = true;
        }
    }

    public void removeEdge(int u, int v) {
        if (u >= 0 && u < numVertices && v >= 0 && v < numVertices) {
            adjMatrix[u][v] = false;
            adjMatrix[v][u] = false;
        }
    }

    public int getDegree(int u) {
        int degree = 0;
        if (u >= 0 && u < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[u][i]) degree++;
            }
        }
        return degree;
    }

    public List<Integer> getNeighbors(int u) {
        List<Integer> neighbors = new ArrayList<>();
        if (u >= 0 && u < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[u][i]) neighbors.add(i);
            }
        }
        return neighbors;
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (adjMatrix[i][j]) count++;
            }
        }
        return count;
    }
}
