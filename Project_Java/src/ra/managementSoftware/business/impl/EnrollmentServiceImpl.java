package ra.managementSoftware.business.impl;

import ra.managementSoftware.business.IEnrollmentService;
import ra.managementSoftware.dao.IEnrollmentDAO;
import ra.managementSoftware.dao.impl.EnrollmentDAOImpl;
import ra.managementSoftware.model.Enrollment;
import ra.managementSoftware.model.EnrollmentImpl;

import java.util.List;

public class EnrollmentServiceImpl implements IEnrollmentService {
    private final IEnrollmentDAO enrollmentDAO;

    public EnrollmentServiceImpl() {
        enrollmentDAO = new EnrollmentDAOImpl();
    }

    @Override
    public List<EnrollmentImpl> findAll() {
        return enrollmentDAO.findAll();
    }

    @Override
    public boolean addEnrollment(int idStudent, int idCourse) {
        return enrollmentDAO.addEnrollment(idStudent, idCourse);
    }

    @Override
    public boolean deleteStudent(int idStudent, int idCourse) {
        return enrollmentDAO.deleteStudent(idStudent, idCourse);
    }

    @Override
    public boolean isEnrollmentExist(int idStudent, int idCourse) {
        return enrollmentDAO.isEnrollmentExist(idStudent, idCourse);
    }

    @Override
    public List<EnrollmentImpl> findEnrollment(int idStudent) {
        return enrollmentDAO.findEnrollment(idStudent);
    }

    @Override
    public String getEnrollmentStatus(int studentId, int courseId) {
        return  enrollmentDAO.getEnrollmentStatus(studentId, courseId);
    }
}
