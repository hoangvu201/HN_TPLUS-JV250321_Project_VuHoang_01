package ra.managementSoftware.dao;

import ra.managementSoftware.model.Enrollment;

import java.util.List;

public interface IEnrollmentDAO {
    List<Enrollment> findAll();
    Enrollment findById(int id);
    boolean create(Enrollment enrollment);
    boolean updateStatus(int enrollmentId, String status);
    boolean delete(int id);
    List<Enrollment> findByStudentId(int studentId); // Optional
    List<Enrollment> findByCourseId(int courseId); // Optional

}
