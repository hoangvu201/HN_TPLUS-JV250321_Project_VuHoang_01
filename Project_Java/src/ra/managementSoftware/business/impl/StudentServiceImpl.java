package ra.managementSoftware.business.impl;

import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.dao.IStudentDAO;
import ra.managementSoftware.dao.impl.StudentDAOImpl;

public class StudentServiceImpl implements IStudentService {
    private final IStudentDAO studentDAO;

    public StudentServiceImpl() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    public boolean checkLoginStudent(String email, String password) {
        return studentDAO.loginStudent(email, password);
    }
}
