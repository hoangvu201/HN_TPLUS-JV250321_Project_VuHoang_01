package ra.managementSoftware.business;

import ra.managementSoftware.model.Student;

import java.util.List;

public interface IStudentService {
    int checkLoginStudent(String email, String password);

    List<Student> getAll();

    Student getById(int id);

    boolean addStudent(Student student);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);

    List<Student> getStudentByEmail(String email);

    List<Student> getStudentByName(String name);

    List<Student> getStudentById(int id);

    boolean checkEmailExist(String email);

    boolean checkIdExist(int id);

    List<Student> sortStudentByName(int number);

    List<Student> sortStudentById(int number);

    String getLastName(String fullName);
}
