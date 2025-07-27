package ra.managementSoftware.business;

import ra.managementSoftware.model.Course;

import java.util.List;

public interface ICourseService {
    List<Course> getAll();

    boolean checkCourseIdExists(int id);

    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean deleteCourse(int id);

    Course getCourseById(int id);

    List<Course> getCourseByName(String name);

    List<Course> sortById(int number);

    List<Course> sortByName(int number);
}
