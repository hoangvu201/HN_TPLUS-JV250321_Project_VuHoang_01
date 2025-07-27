package ra.managementSoftware.dao;

import ra.managementSoftware.model.Student;

import java.util.List;

public interface IStudentDAO {
    int loginStudent(String email, String password);

    List<Student> getAll();

    Student getById(int id);

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    List<Student> findStudentByEmail(String email);

    List<Student> findStudentByName(String name);

    List<Student> findStudentById(int choice);

    boolean checkEmailExist(String email);

    boolean checkIdExist(int id);

    List<Student> sortStudentById(int number);
}
