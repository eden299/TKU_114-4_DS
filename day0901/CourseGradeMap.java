package day0901;

import java.util.*;

public class CourseGradeMap {
    private Map<String, List<Integer>> courseGrades;

    public CourseGradeMap() {
        this.courseGrades = new HashMap<>();
    }

    public void addGrade(String courseId, int grade) {
        courseGrades.computeIfAbsent(courseId, k -> new ArrayList<>()).add(grade);
    }

    public double getAverage(String courseId) {
        List<Integer> grades = courseGrades.get(courseId);
        if (grades == null || grades.isEmpty()) return 0.0;
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    public int getMaxGrade(String courseId) {
        List<Integer> grades = courseGrades.get(courseId);
        if (grades == null || grades.isEmpty()) return -1;
        int max = Integer.MIN_VALUE;
        for (int g : grades) {
            if (g > max) max = g;
        }
        return max;
    }

    public void printReportSortedByCourseId() {
        List<String> sortedCourses = new ArrayList<>(courseGrades.keySet());
        Collections.sort(sortedCourses);
        for (String courseId : sortedCourses) {
            System.out.println("Course: " + courseId + 
                               ", Avg: " + getAverage(courseId) + 
                               ", Max: " + getMaxGrade(courseId));
        }
    }
}
