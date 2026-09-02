package day0901;

import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {
    private String[] stationNames;
    private boolean[][] adjMatrix;
    private int numStations;

    public MetroMatrixGraph(String[] stationNames) {
        this.numStations = stationNames.length;
        this.stationNames = stationNames.clone();
        this.adjMatrix = new boolean[numStations][numStations];
    }

    private int getIndex(String station) {
        for (int i = 0; i < numStations; i++) {
            if (stationNames[i].equals(station)) return i;
        }
        return -1;
    }

    public void addEdge(String station1, String station2) {
        int u = getIndex(station1);
        int v = getIndex(station2);
        if (u != -1 && v != -1 && u != v) {
            adjMatrix[u][v] = true;
            adjMatrix[v][u] = true;
        }
    }

    public List<String> getNeighbors(String station) {
        List<String> neighbors = new ArrayList<>();
        int u = getIndex(station);
        if (u != -1) {
            for (int v = 0; v < numStations; v++) {
                if (adjMatrix[u][v]) {
                    neighbors.add(stationNames[v]);
                }
            }
        }
        return neighbors;
    }

    public int getDegree(String station) {
        return getNeighbors(station).size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < numStations; i++) {
            for (int j = i + 1; j < numStations; j++) {
                if (adjMatrix[i][j]) count++;
            }
        }
        return count;
    }

    public void printMatrixReport() {
        System.out.println("=== Metro Matrix Report ===");
        System.out.print("\t");
        for (String name : stationNames) {
            System.out.print(name + "\t");
        }
        System.out.println();

        for (int i = 0; i < numStations; i++) {
            System.out.print(stationNames[i] + "\t");
            for (int j = 0; j < numStations; j++) {
                System.out.print((adjMatrix[i][j] ? 1 : 0) + "\t");
            }
            System.out.println();
        }
    }
}
