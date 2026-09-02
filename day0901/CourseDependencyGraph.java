package day0901;

import java.util.*;

public class CourseDependencyGraph {
    private Map<String, Set<String>> adjList;
    private Set<String> courses;

    public CourseDependencyGraph() {
        this.adjList = new HashMap<>();
        this.courses = new HashSet<>();
    }

    public void addCourse(String course) {
        courses.add(course);
        adjList.putIfAbsent(course, new HashSet<>());
    }

    public void addDependency(String prerequisite, String course) {
        addCourse(prerequisite);
        addCourse(course);
        adjList.get(prerequisite).add(course);
    }

    public Set<String> getPrerequisites(String course) {
        Set<String> prereqs = new HashSet<>();
        for (Map.Entry<String, Set<String>> entry : adjList.entrySet()) {
            if (entry.getValue().contains(course)) {
                prereqs.add(entry.getKey());
            }
        }
        return prereqs;
    }

    public Set<String> getNextCourses(String course) {
        return adjList.getOrDefault(course, Collections.emptySet());
    }

    public int getInDegree(String course) {
        return getPrerequisites(course).size();
    }

    public int getOutDegree(String course) {
        return getNextCourses(course).size();
    }
}
