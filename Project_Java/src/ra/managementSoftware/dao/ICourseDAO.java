package ra.managementSoftware.dao;

import ra.managementSoftware.model.Course;

import java.util.List;

public interface ICourseDAO {
    List<Course> getAll();

    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(int id);

    boolean checkCourseIdExists(int id);

    List<Course> getCourseByName(String name);

    Course getCourseById(int id);

    List<Course> sortById(int number);

    List<Course> sortByName(int number);
}
