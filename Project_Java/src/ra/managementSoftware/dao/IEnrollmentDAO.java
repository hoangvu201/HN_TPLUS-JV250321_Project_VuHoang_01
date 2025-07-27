package ra.managementSoftware.dao;

import ra.managementSoftware.model.Enrollment;
import ra.managementSoftware.model.EnrollmentImpl;

import java.util.List;

public interface IEnrollmentDAO {
    List<EnrollmentImpl> findAll();

    boolean addEnrollment(int idStudent, int idCourse);

    boolean deleteStudent(int idStudent, int idCourse);

    boolean isEnrollmentExist(int idStudent, int idCourse);

    List<EnrollmentImpl> findEnrollment(int idStudent);

    boolean getStatusByIdEnrollment(int idEnrollment);

    String getEnrollmentStatus(int studentId, int courseId);
}
