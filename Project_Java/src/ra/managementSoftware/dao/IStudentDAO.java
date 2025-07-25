package ra.managementSoftware.dao;

import ra.managementSoftware.model.Student;

import java.util.List;

public interface IStudentDAO {
    boolean loginStudent(String email,String password);
    List<Student> getAll();
    Student getById(int id);
    boolean addStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(int id);
}
