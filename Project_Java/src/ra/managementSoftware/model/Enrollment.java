package ra.managementSoftware.model;

import java.time.LocalDateTime;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDateTime registered_at ;
    private EnrollmentStatus status;

    public Enrollment() {
    }

    public Enrollment(int id, int studentId, int courseId, LocalDateTime registered_at, EnrollmentStatus status) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registered_at = registered_at;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getRegistered_at() {
        return registered_at;
    }

    public void setRegistered_at(LocalDateTime registered_at) {
        this.registered_at = registered_at;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("| %-7d | %-7d | %-7d | %-15s | %-15s |",
                this.id, this.studentId, this.courseId, this.registered_at,this.status);
    }

}
