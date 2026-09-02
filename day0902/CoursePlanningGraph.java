package day0902;

import java.util.*;

public class CoursePlanningGraph {
    private Map<String, List<String>> adjList = new HashMap<>();

    public void addCourse(String course) {
        adjList.putIfAbsent(course, new ArrayList<>());
    }

    public void addPrerequisite(String prereq, String course) {
        addCourse(prereq);
        addCourse(course);
        adjList.get(prereq).add(course);
    }

    public List<String> getAffectedCourses(String course) {
        List<String> affected = new ArrayList<>();
        if (!adjList.containsKey(course)) return affected;

        Set<String> visited = new HashSet<>();
        dfs(course, visited, affected);
        if (!affected.isEmpty()) {
            affected.remove(0);
        }
        return affected;
    }

    private void dfs(String curr, Set<String> visited, List<String> result) {
        visited.add(curr);
        result.add(curr);
        for (String neighbor : adjList.getOrDefault(curr, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, result);
            }
        }
    }
}
