package day0820;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Enrollment {
    private String studentId;
    private String courseCode;

    public Enrollment(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() { return studentId; }
    public String getCourseCode() { return courseCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) && 
               Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseCode);
    }

    @Override
    public String toString() {
        return "Enrollment{studentId='" + studentId + "', courseCode='" + courseCode + "'}";
    }
}

public class EnrollmentSetSystem {
    public static void main(String[] args) {
        Set<Enrollment> enrollments = new HashSet<>();

        boolean addResult1 = enrollments.add(new Enrollment("S001", "CS101"));
        boolean addResult2 = enrollments.add(new Enrollment("S001", "CS102"));
        System.out.println("S001 加入 CS101: " + addResult1); 
        System.out.println("S001 加入 CS102: " + addResult2);

        boolean addResult3 = enrollments.add(new Enrollment("S001", "CS101"));
        System.out.println("S001 重複加入 CS101: " + addResult3);

        Enrollment testTarget = new Enrollment("S001", "CS101");
        
        System.out.println("是否存在與 testTarget 相同身分的報名: " + enrollments.contains(testTarget)); 
        
        boolean removeResult = enrollments.remove(testTarget);
        System.out.println("取消報名 testTarget 結果: " + removeResult);
        System.out.println("再次測試是否存在: " + enrollments.contains(testTarget));
    }
}
