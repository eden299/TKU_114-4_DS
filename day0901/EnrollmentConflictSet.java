package day0901;

import java.util.*;

public class EnrollmentConflictSet {
    public static class EnrollmentKey {
        private String studentId;
        private String courseId;

        public EnrollmentKey(String studentId, String courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        public String getStudentId() { return studentId; }
        public String getCourseId() { return courseId; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EnrollmentKey that = (EnrollmentKey) o;
            return Objects.equals(studentId, that.studentId) && Objects.equals(courseId, that.courseId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }

    private Set<EnrollmentKey> uniqueEnrollments = new HashSet<>();
    private List<EnrollmentKey> duplicateRecords = new ArrayList<>();
    private Map<String, Set<String>> studentCourses = new HashMap<>();
    private Map<String, Set<String>> courseStudents = new HashMap<>();

    public void addEnrollment(String studentId, String courseId) {
        EnrollmentKey key = new EnrollmentKey(studentId, courseId);
        if (!uniqueEnrollments.add(key)) {
            duplicateRecords.add(key);
        } else {
            studentCourses.computeIfAbsent(studentId, k -> new HashSet<>()).add(courseId);
            courseStudents.computeIfAbsent(courseId, k -> new HashSet<>()).add(studentId);
        }
    }

    public List<EnrollmentKey> getDuplicateRecords() {
        return duplicateRecords;
    }

    public Set<String> getStudentCourses(String studentId) {
        return studentCourses.getOrDefault(studentId, Collections.emptySet());
    }

    public int getCourseStudentCount(String courseId) {
        Set<String> students = courseStudents.get(courseId);
        return students == null ? 0 : students.size();
    }
}
