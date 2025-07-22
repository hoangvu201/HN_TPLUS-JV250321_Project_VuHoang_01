package ra.managementSoftware.dao;

import ra.managementSoftware.model.Course;

import java.util.List;

public interface ICourseDAO {
    List<Course> getAll();
    Course getById(int id);
    boolean addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
}
