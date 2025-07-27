package ra.managementSoftware.business.impl;

import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.dao.IStudentDAO;
import ra.managementSoftware.dao.impl.StudentDAOImpl;
import ra.managementSoftware.model.Student;

import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private final IStudentDAO studentDAO;

    public StudentServiceImpl() {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    public int checkLoginStudent(String email, String password) {
        return studentDAO.loginStudent(email, password);
    }

    @Override
    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public Student getById(int id) {
        return studentDAO.getById(id);
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDAO.addStudent(student);
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(int id) {
        return studentDAO.deleteStudent(id);
    }

    @Override
    public List<Student> getStudentByEmail(String email) {
        return studentDAO.findStudentByEmail(email);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentDAO.findStudentByName(name);
    }

    @Override
    public List<Student> getStudentById(int id) {
        return studentDAO.findStudentById(id);
    }

    @Override
    public boolean checkEmailExist(String email) {
        return studentDAO.checkEmailExist(email);
    }

    @Override
    public boolean checkIdExist(int id) {
        return studentDAO.checkIdExist(id);
    }

    @Override
    public List<Student> sortStudentByName(int number) {
        List<Student> students = studentDAO.getAll();
        return students.stream()
                .sorted((s1, s2) -> {
                    String name1 = getLastName(s1.getName());
                    String name2 = getLastName(s2.getName());
                    return name1.compareToIgnoreCase(name2);
                }).toList();
    }

    @Override
    public List<Student> sortStudentById(int number) {
        return studentDAO.sortStudentById(number);
    }

    @Override
    public String getLastName(String fullName) {
        String[] parts = fullName.split(" ");
        return parts[parts.length - 1];
    }
}
