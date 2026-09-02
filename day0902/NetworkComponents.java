package day0902;

import java.util.*;

public class NetworkComponents {
    public static class ComponentReport {
        public List<Set<String>> components = new ArrayList<>();
        public int componentCount = 0;
        public Set<String> maxComponent = new HashSet<>();
    }

    public static ComponentReport analyzeComponents(Map<String, List<String>> graph) {
        ComponentReport report = new ComponentReport();
        if (graph == null || graph.isEmpty()) return report;

        Set<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                Set<String> comp = new HashSet<>();
                Queue<String> queue = new LinkedList<>();

                queue.offer(node);
                visited.add(node);

                while (!queue.isEmpty()) {
                    String curr = queue.poll();
                    comp.add(curr);

                    for (String neighbor : graph.getOrDefault(curr, Collections.emptyList())) {
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }

                report.components.add(comp);
                if (comp.size() > report.maxComponent.size()) {
                    report.maxComponent = comp;
                }
            }
        }
        report.componentCount = report.components.size();
        return report;
    }
}
