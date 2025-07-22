package ra.managementSoftware.model;

import java.time.LocalDateTime;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDateTime registered_at ;

    public Enrollment() {
    }

    public Enrollment(int id, int studentId, int courseId, LocalDateTime registered_at) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.registered_at = registered_at;
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
}
