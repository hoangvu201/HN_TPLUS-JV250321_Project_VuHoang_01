package ra.managementSoftware.model;

import java.time.LocalDateTime;

public class EnrollmentImpl extends Enrollment{
    private String studentName;
    private String courseName;

    public EnrollmentImpl() {
        super();
    }

    public EnrollmentImpl(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public EnrollmentImpl(int id, int studentId, int courseId, LocalDateTime registered_at, EnrollmentStatus status, String studentName, String courseName) {
        super(id, studentId, courseId, registered_at, status);
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return String.format("| %-7d | %-15d | %-15s | %-15d | %-30s | %-20s | %-15s |",
                super.getId(), super.getStudentId(),this.studentName, super.getCourseId(),this.courseName, super.getRegistered_at(),super.getStatus());
    }
}
